package com.cuyer.calitracker;

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

import com.cuyer.calitracker.Controller.BMICalcUtil;


public class BmiImperial extends Fragment {


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bmi_imperial, container, false);

        Button calculateButton = (Button)view.findViewById(R.id.calculateBmiButtonImperial);
        EditText heightEditText = (EditText) view.findViewById(R.id.heightFtEditText);
        EditText height2EditText = (EditText) view.findViewById(R.id.heightInEditText);
        EditText weightEditText = (EditText) view.findViewById(R.id.weightPoundsEditText);
        TextView bmiTextView = (TextView) view.findViewById(R.id.BmiTextViewImperial);
        TextView bmi2TextView = (TextView) view.findViewById(R.id.BmiHealthTextViewImperial);




        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(weightEditText.length() == 0 || heightEditText.length() == 0  || height2EditText.length() == 0){
                    Toast.makeText(getActivity(),
                                    "Populate Weight and Height to Calculate BMI", Toast.LENGTH_SHORT)
                            .show();
                }else{
                    double heightInFt = Double.parseDouble(heightEditText.getText().toString());
                    double heightInIn = Double.parseDouble(height2EditText.getText().toString());
                    double weightInPounds = Double.parseDouble(weightEditText.getText().toString());
                    double bmi = BMICalcUtil.getInstance().calculateBMIImperial(heightInFt,heightInIn,weightInPounds);
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
                height2EditText.setBackgroundResource(R.drawable.border);
                heightEditText.setBackgroundResource(R.drawable.border_black);

                return false;
            }
        });

        weightEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                weightEditText.setBackgroundResource(R.drawable.border_black);
                heightEditText.setBackgroundResource(R.drawable.border);
                height2EditText.setBackgroundResource(R.drawable.border);

                return false;
            }
        });

        height2EditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                weightEditText.setBackgroundResource(R.drawable.border);
                heightEditText.setBackgroundResource(R.drawable.border);
                height2EditText.setBackgroundResource(R.drawable.border_black);

                return false;
            }
        });




        return view;
    }
}