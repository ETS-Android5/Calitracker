package com.progresstracking.calitracker.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.progresstracking.calitracker.Controller.BMICalcUtil;
import com.progresstracking.calitracker.R;


public class BmiFragment extends Fragment {



    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bmi, container, false);

        Button calculateButton = (Button)view.findViewById(R.id.calculateBmiButton);
        EditText heightEditText = (EditText) view.findViewById(R.id.heightCmEditText);
        EditText weightEditText = (EditText) view.findViewById(R.id.weightKgEditText);
        TextView bmiTextView = (TextView) view.findViewById(R.id.BmiTextView);
        TextView bmi2TextView = (TextView) view.findViewById(R.id.BmiHealthTextView);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(weightEditText.length() == 0 || heightEditText.length() == 0){
                    Toast.makeText(getActivity(),
                            "Populate Weight and Height to Calculate BMI", Toast.LENGTH_SHORT)
                            .show();
                }else{
                    double heightInCms = Double.parseDouble(heightEditText.getText().toString());
                    double weightInKgs = Double.parseDouble(weightEditText.getText().toString());
                    double bmi = BMICalcUtil.getInstance().calculateBMIMetric(heightInCms,
                            weightInKgs);
                    displayBMI(bmi);
                }


            }
            private void displayBMI(double bmi){

                bmiTextView.setText(String.format("%.2f", bmi));

                String bmiCategory = BMICalcUtil.getInstance().classifyBMI(bmi);
                bmi2TextView.setText(bmiCategory);

                bmiTextView.setVisibility(View.VISIBLE);
                bmi2TextView.setVisibility(View.VISIBLE);

            }

        });

        heightEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                weightEditText.setBackgroundResource(R.drawable.border);
                heightEditText.setBackgroundResource(R.drawable.border_black);

                return false;
            }
        });

        weightEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                weightEditText.setBackgroundResource(R.drawable.border_black);
                heightEditText.setBackgroundResource(R.drawable.border);

                return false;
            }
        });







        // Inflate the layout for this fragment
        return view;
    }


}