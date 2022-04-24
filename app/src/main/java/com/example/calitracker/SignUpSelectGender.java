package com.example.calitracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        femaleTextView.setOnTouchListener((view, motionEvent) -> {
            femaleTextView.setBackgroundResource(R.drawable.border_black);
            maleTextView.setBackgroundResource(R.drawable.border);
            return false;
        });

        maleTextView.setOnTouchListener((view, motionEvent) -> {
            maleTextView.setBackgroundResource(R.drawable.border_black);
            femaleTextView.setBackgroundResource(R.drawable.border);
            return false;
        });

        femaleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            //TODO update plci jest zrobiony na kliknieciu w przycisk, a powinno byc po nacisnieciu next
                //TODO mozna to zrobic poprzez boolean https://stackoverflow.com/questions/30661425/checking-if-a-button-has-been-clicked
                Map<String, Object> gender = new HashMap<>();
                gender.put("Gender", "Female");

                auth.signInWithEmailAndPassword(EmailAndPass.email,EmailAndPass.pass);
                FirebaseUser user = auth.getCurrentUser();
                DocumentReference genderRef = db.collection("users").
                        document(user.getUid());

                genderRef.update(gender);

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
                startActivity(new Intent(SignUpSelectGender.this,
                        SignUpSelectMetric.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });





    }
}