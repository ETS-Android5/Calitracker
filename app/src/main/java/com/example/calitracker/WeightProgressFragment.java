package com.example.calitracker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.calitracker.Controller.TextViewDrawer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class WeightProgressFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference("chartTable");
    LineGraphSeries lineGraphSeries;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    final Calendar myCalendar= Calendar.getInstance();
    FirebaseAuth auth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight_progress, container,
                false);


        FloatingActionButton floatingActionButton = (FloatingActionButton) view
                .findViewById(R.id.add_weight_floatingActionButton);
       TextView textView = new TextView(getActivity());
       LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayoutWeightProgress);

        GraphView graphView = (GraphView)view.findViewById(R.id.graph);
        lineGraphSeries = new LineGraphSeries();
        graphView.addSeries(lineGraphSeries);
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(){
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if(isValueX){
                    return sdf.format(new Date((long) value));
                }else{
                    return super.formatLabel(value, isValueX);
                }

            }
        });
        graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
        graphView.getGridLabelRenderer().setNumVerticalLabels(3);
        graphView.getViewport().setBackgroundColor(Color.WHITE);
        graphView.getGridLabelRenderer().setGridColor(Color.TRANSPARENT);
        graphView.getViewport().setDrawBorder(true);
        graphView.getViewport().setBorderColor(Color.BLACK);
        graphView.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();

            }





            private TextView createNewTextView(String weight, String date){
                final LinearLayout.LayoutParams layoutParams = new LinearLayout
                        .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                TextView textView1 = new TextView(getActivity());
                textView1.setLayoutParams(layoutParams);
                textView1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.roboto);
                textView1.setText(weight + " " + date);
                return textView1;

            }






            private void showDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                        R.style.AlertDialogTheme);
                final Context context = builder.getContext();
                final LayoutInflater layoutInflater = LayoutInflater.from(context);
                final View view = inflater.inflate(R.layout.add_weight_layout,
                        null, false);

               final EditText weightEditText = (EditText)view
                       .findViewById(R.id.WeightEditTextNumberDecimal);
               final EditText dateEditText = (EditText)view
                       .findViewById(R.id.WeightDateEditTextDate);


                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH,day);
                        dateEditText.setText(sdf.format(myCalendar.getTime()));
                    }
                };


               dateEditText.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       new DatePickerDialog(getActivity(),date,myCalendar.get(Calendar.YEAR),
                               myCalendar.get(Calendar.MONTH),myCalendar
                               .get(Calendar.DAY_OF_MONTH)).show();
                   }
               });




                //TODO  dane posortować przed wyrzuceniem na wykres dodatkowo trza bedzie do bazy danych wrzucac te dane o wadze i dacie pod konkretne userID
                //TODO bo tera to po calej bazie danych mi petla sie robi

                builder.setPositiveButton("ADD",
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            if(TextUtils.isEmpty(weightEditText.getText().toString()) || TextUtils
                                    .isEmpty(dateEditText.getText().toString())) {

                                Toast.makeText(getActivity(),"Please provide weight and date",
                                        Toast.LENGTH_SHORT).show();

                            } else{
                                linearLayout.addView(createNewTextView(weightEditText.getText().toString(),
                                        dateEditText.getText().toString()));
                                String id = reference.push().getKey();

                                try {
                                    Date date = sdf.parse(dateEditText.getText().toString());
                                    long x = date.getTime();
                                    int y = Integer.parseInt(weightEditText.getText().toString());
                                    PointValue pointValue = new PointValue(x,y);
                                    reference.child(id).setValue(pointValue);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }

                    }

                });
                builder.setView(view);

                builder.show();
            }




        });



        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int index = 0;
                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                for(DataSnapshot myDataSnapshot : snapshot.getChildren()){
                    PointValue pointValue = myDataSnapshot.getValue(PointValue.class);
                    dp[index] = new DataPoint(pointValue.getxValue(), pointValue.getyValue());
                    String str1 = Integer.toString(pointValue.getyValue());
                    String str2 = sdf.format(new Date(pointValue.getxValue()));
                    linearLayout.addView(createTextView(str1,str2));
                    index ++;
                }

            }

            private TextView createTextView(String weight, String date) {
                final LinearLayout.LayoutParams layoutParams = new LinearLayout
                        .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                TextView textView1 = new TextView(getActivity());
                textView1.setLayoutParams(layoutParams);
                textView1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.roboto);
                textView1.setText(weight + " " + date);
                return textView1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }

    @Override
    public void onStart() {






        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;
                for(DataSnapshot myDataSnapshot : snapshot.getChildren()){
                    PointValue pointValue = myDataSnapshot.getValue(PointValue.class);
                    dp[index] = new DataPoint(pointValue.getxValue(), pointValue.getyValue());
                    index ++;
                }
                lineGraphSeries.resetData(dp);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        super.onStart();

    }

}