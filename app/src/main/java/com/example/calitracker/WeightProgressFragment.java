package com.example.calitracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeightProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight_progress, container,
                false);


        FloatingActionButton floatingActionButton = (FloatingActionButton) view
                .findViewById(R.id.add_weight_floatingActionButton);
       TextView textView = new TextView(getActivity());
       LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.linearLayoutWeightProgress);



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

                            }

                    }

                });
                builder.setView(view);

                builder.show();
            }

        });


        return view;
    }

}