package com.example.calitracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.calitracker.Model.EmailAndPass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SignUpSelectGender extends AppCompatActivity {
    private FirebaseAuth auth;
    private boolean male_clicked = false;
    private boolean female_clicked = false;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_select_gender);
        Objects.requireNonNull(getSupportActionBar()).hide();
        TextView preferNotToSay, nextTextView, femaleTextView, maleTextView;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();


        preferNotToSay = findViewById(R.id.prefer_not_to_say_textview);
        nextTextView = findViewById(R.id.next_textview);
        femaleTextView = findViewById(R.id.female_textview);
        maleTextView = findViewById(R.id.male_textview);


        femaleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                femaleTextView.setBackgroundResource(R.drawable.border_black);
                maleTextView.setBackgroundResource(R.drawable.border);
                female_clicked = true;
                male_clicked = false;
            }
        });

        maleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maleTextView.setBackgroundResource(R.drawable.border_black);
                femaleTextView.setBackgroundResource(R.drawable.border);
                male_clicked = true;
                female_clicked = false;


            }
        });

        preferNotToSay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpSelectGender.this,
                        SignUpSelectMetric.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });

        nextTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(male_clicked){
                    Map<String, Object> gender = new HashMap<>();
                    gender.put("Gender", "Male");

                    auth.signInWithEmailAndPassword(EmailAndPass.email,EmailAndPass.pass);
                    FirebaseUser user = auth.getCurrentUser();
                    DocumentReference genderRef = db.collection("users").
                            document(user.getUid());

                    genderRef.update(gender);
                }

                if(female_clicked){
                    Map<String, Object> gender = new HashMap<>();
                    gender.put("Gender", "Female");

                    auth.signInWithEmailAndPassword(EmailAndPass.email,EmailAndPass.pass);
                    FirebaseUser user = auth.getCurrentUser();
                    DocumentReference genderRef = db.collection("users").
                            document(user.getUid());

                    genderRef.update(gender);
                }


                startActivity(new Intent(SignUpSelectGender.this,
                        SignUpSelectMetric.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });


    }
}