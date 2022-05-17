package com.cuyer.calitracker;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cuyer.calitracker.Controller.BMRCalcUtil;
import com.cuyer.calitracker.Model.EmailAndPass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class BmrImperial extends Fragment {

    private FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bmr_imperial, container, false);





        Button calculateButton = (Button)view.findViewById(R.id.calculateBmiButtonImperial);
        EditText heightEditText = (EditText) view.findViewById(R.id.heightFtEditText);
        EditText height2EditText = (EditText) view.findViewById(R.id.heightInEditText);
        EditText weightEditText = (EditText) view.findViewById(R.id.weightPoundsEditText);
        EditText ageEditText = (EditText) view.findViewById(R.id.ageEditTextImperial);
        TextView bmiTextView = (TextView) view.findViewById(R.id.BmiTextViewImperial);


        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        db.collection("users").document(user.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Log.d("data", "DocumentSnapshot data: " + document
                                        .getData());

                                EmailAndPass.gender = document.get("Gender").toString();


                            }else{
                                Log.d("data", "No such document");
                            }
                        }else{
                            Log.d("data", "get failed with ", task.getException());
                        }
                    }
                });






        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(weightEditText.length() == 0 || heightEditText.length() == 0 || ageEditText
                        .length() == 0 || height2EditText.length() == 0){
                    Toast.makeText(getActivity(),
                                    "Populate Weight, Height and Age to Calculate BMR",
                                    Toast.LENGTH_SHORT)
                            .show();
                }else{
                    double heightInFt = Double.parseDouble(heightEditText.getText().toString());
                    double heightInIn = Double.parseDouble(height2EditText.getText().toString());
                    double weightInPounds = Double.parseDouble(weightEditText.getText().toString());
                    int age = Integer.parseInt(ageEditText.getText().toString());

                    if(EmailAndPass.gender.equals("Male")) {
                        double bmr = BMRCalcUtil.getInstance()
                                .calculateBMRImperialMale(heightInFt,heightInIn,weightInPounds,age);
                        displayBMR(bmr);
                    }else if (EmailAndPass.gender.equals("Female")){
                        double bmr = BMRCalcUtil.getInstance()
                                .calculateBMRImperialFemale(heightInFt,heightInIn,weightInPounds,age);
                        displayBMR(bmr);
                    }else{
                        Toast.makeText(getActivity(), "Please assign your gender in the Settings",
                                Toast.LENGTH_SHORT).show();
                    }

                }


            }
            private void displayBMR(double bmr){

                bmiTextView.setText("BMR = " + String.format("%.2f", bmr));
                bmiTextView.setVisibility(View.VISIBLE);


            }

        });


        ageEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                ageEditText.setBackgroundResource(R.drawable.border_black);
                weightEditText.setBackgroundResource(R.drawable.border);
                heightEditText.setBackgroundResource(R.drawable.border);
                height2EditText.setBackgroundResource(R.drawable.border);

                return false;
            }
        });

        heightEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ageEditText.setBackgroundResource(R.drawable.border);
                weightEditText.setBackgroundResource(R.drawable.border);
                heightEditText.setBackgroundResource(R.drawable.border_black);
                height2EditText.setBackgroundResource(R.drawable.border);

                return false;
            }
        });

        height2EditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ageEditText.setBackgroundResource(R.drawable.border);
                weightEditText.setBackgroundResource(R.drawable.border);
                heightEditText.setBackgroundResource(R.drawable.border);
                height2EditText.setBackgroundResource(R.drawable.border_black);

                return false;
            }
        });

        weightEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                ageEditText.setBackgroundResource(R.drawable.border);
                weightEditText.setBackgroundResource(R.drawable.border_black);
                heightEditText.setBackgroundResource(R.drawable.border);
                height2EditText.setBackgroundResource(R.drawable.border);

                return false;
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}