package com.cuyer.calitracker.View;

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

import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.cuyer.calitracker.Model.EmailAndPass;
import com.cuyer.calitracker.Model.PointValue;
import com.cuyer.calitracker.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class WeightProgressFragment extends Fragment {
    int style = DateFormat.MEDIUM;
    Date date = new Date();
    DateFormat df;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    GraphView graphView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseReference reference = database.getReference("users");
    LineGraphSeries lineGraphSeries;
    SimpleDateFormat sdf2 = new SimpleDateFormat("MMM dd", Locale.ENGLISH);
    final Calendar myCalendar = Calendar.getInstance();
    FirebaseAuth auth;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        df = DateFormat.getDateInstance(style, Locale.getDefault());

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight_progress, container,
                false);

        TextView youLostTV = (TextView)view.findViewById(R.id.you_lost_TV);


                FloatingActionButton floatingActionButton = (FloatingActionButton) view
                .findViewById(R.id.add_weight_floatingActionButton);
        FloatingActionButton floatingDeleteActionButton = (FloatingActionButton) view
                .findViewById(R.id.delete_weight_floatingActionButton);
        TextView textView = new TextView(getActivity());
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linearLayoutWeightProgress);

        graphView = view.findViewById(R.id.graph);
        //GraphView graphView = (GraphView) view.findViewById(R.id.graph);
        lineGraphSeries = new LineGraphSeries();
        lineGraphSeries.setDrawDataPoints(true);
        lineGraphSeries.setDrawBackground(true);
        lineGraphSeries.setBackgroundColor(Color.argb(60,95,226,156));
        lineGraphSeries.setDataPointsRadius(10);
        lineGraphSeries.setThickness(6);
        graphView.addSeries(lineGraphSeries);



        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return sdf2.format(new Date((long) value));
                } else {
                    return super.formatLabel(value, isValueX);
                }

            }
        });
        graphView.getGridLabelRenderer().setNumHorizontalLabels(4);
        graphView.getGridLabelRenderer().setNumVerticalLabels(4);
        graphView.getViewport().setBackgroundColor(Color.WHITE);
        graphView.getViewport().setDrawBorder(true);
        graphView.getViewport().setBorderColor(Color.BLACK);
        graphView.getGridLabelRenderer().setVerticalLabelsColor(Color.WHITE);
        graphView.getGridLabelRenderer().setHorizontalLabelsColor(Color.WHITE);
        graphView.getGridLabelRenderer().setPadding(40);
        graphView.getGridLabelRenderer().setHorizontalLabelsAngle(50);



        // activate horizontal zooming and scrolling
        graphView.getViewport().setScalable(true);

// activate horizontal scrolling
        graphView.getViewport().setScrollable(true);

// activate horizontal and vertical zooming and scrolling
        graphView.getViewport().setScalableY(true);

// activate vertical scrolling
        graphView.getViewport().setScrollableY(true);








        db.collection("users").document(user.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Log.d("data", "DocumentSnapshot data: " + document.getData());

                                EmailAndPass.metric = document.get("Metric").toString();


                            }else{
                                Log.d("data", "No such document");
                            }
                        }else{
                            Log.d("data", "get failed with ", task.getException());
                        }
                    }
                });





        floatingDeleteActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDeleteDialog();

            }



            private void showDeleteDialog(){

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                        R.style.AlertDialogTheme);
                builder.setMessage("Are you sure that you want to delete all the data?")
                        .setTitle("Deleting data");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        linearLayout.setVisibility(View.INVISIBLE);

                        String id = user.getUid();
                        reference.child(id).removeValue();


                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });



                builder.show();
            }




        });



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog();

            }


            private TextView createNewTextView(String weight, String date) {
                final LinearLayout.LayoutParams layoutParams = new LinearLayout
                        .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                TextView textView1 = new TextView(getActivity());
                textView1.setLayoutParams(layoutParams);
                textView1.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
                Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.roboto);
                textView1.setText(weight + " " + EmailAndPass.metric + "       " + date);
                return textView1;

            }


                PointValue pointValue;

            private void showDialog() {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                        R.style.AlertDialogTheme);
                final Context context = builder.getContext();
                final LayoutInflater layoutInflater = LayoutInflater.from(context);
                final View view = inflater.inflate(R.layout.add_weight_layout,
                        null, false);

                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(6);

                final EditText weightEditText = (EditText) view
                        .findViewById(R.id.WeightEditTextNumberDecimal);
                final EditText dateEditText = (EditText) view
                        .findViewById(R.id.WeightDateEditTextDate);

                weightEditText.setFilters(filterArray);



                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, day);
                        dateEditText.setText(df.format(myCalendar.getTime()));
                    }
                };


                dateEditText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                                date, myCalendar.get(Calendar.YEAR),
                                myCalendar.get(Calendar.MONTH), myCalendar
                                .get(Calendar.DAY_OF_MONTH));
                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                        datePickerDialog.show();

                    }
                });


                builder.setPositiveButton("ADD",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String id = user.getUid();



                                if (TextUtils.isEmpty(weightEditText.getText().toString()) || TextUtils
                                        .isEmpty(dateEditText.getText().toString())) {

                                    Toast.makeText(getActivity(), "Please provide weight and date",
                                            Toast.LENGTH_SHORT).show();





                                }

                                else {
                                    linearLayout.addView(createNewTextView(weightEditText.getText().toString(),
                                            dateEditText.getText().toString()));


                                    String randomId = reference.push().getKey();

                                    try {
                                        Date date = df.parse(dateEditText.getText().toString());
                                        long x = date.getTime();
                                        float y = Float.parseFloat(weightEditText.getText().toString());
                                        PointValue pointValue = new PointValue(x, y);
                                        reference.child(id).child(randomId).setValue(pointValue);
                                        linearLayout.setVisibility(View.VISIBLE);
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

        String id = user.getUid();
        Query dateAscendingOrder = database.getReference("users")
                .child(id).orderByChild("xValue");

        dateAscendingOrder.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String id = user.getUid();
                int index = 0;

                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                for (DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                    PointValue pointValue = myDataSnapshot.getValue(PointValue.class);
                    dp[index] = new DataPoint(pointValue.getxValue(), pointValue.getyValue());
                    String str1 = Float.toString(pointValue.getyValue());
                    String str2 = df.format(new Date(pointValue.getxValue()));
                    linearLayout.addView(createTextView(str1, str2));
                    index++;

                }

                DecimalFormat df = new DecimalFormat("#.##");
                youLostTV.bringToFront();
                if(index == 0){
                    youLostTV.setText("You have lost/gained 0 " + EmailAndPass.metric );
                } else if(index == 1){
                    youLostTV.setText("You have lost/gained 0 " + EmailAndPass.metric );
                } else if(index > 1) {
                    if (dp[0].getY() - dp[index-1].getY() >= 0) {
                        float lost = (float) (dp[0].getY() - dp[index-1].getY());
                        youLostTV.setText("You have lost " + df.format(lost) + " " + EmailAndPass.metric);
                    }else{
                        float gained = (float) -(dp[0].getY() - dp[index-1].getY());
                        youLostTV.setText("You have gained " + df.format(gained) + " " + EmailAndPass.metric);
                    }

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
                textView1.setText(weight + " " + EmailAndPass.metric + "       " + date);
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
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String id = user.getUid();

        Query dateAscendingOrder = database.getReference("users")
                .child(id).orderByChild("xValue");

        dateAscendingOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DataPoint[] dp = new DataPoint[(int) snapshot.getChildrenCount()];
                int index = 0;
                for (DataSnapshot myDataSnapshot : snapshot.getChildren()){

                    PointValue pointValue = myDataSnapshot.getValue(PointValue.class);
                    dp[index] = new DataPoint(pointValue.getxValue(), pointValue.getyValue());
                    index++;
                }

                lineGraphSeries.resetData(dp);

                graphView.findViewById(R.id.graph);

                if(index > 0) {
                    graphView.getViewport().setMinX(dp[0].getX());
                    graphView.getViewport().setMaxX(dp[index - 1].getX());
                    graphView.getViewport().setXAxisBoundsManual(true);
                    graphView.onDataChanged(true, false);

                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();

    }

}