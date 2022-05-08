package com.example.calitracker.View;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calitracker.Model.EmailAndPass;
import com.example.calitracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class Sign_Up extends AppCompatActivity {


EditText SignUpMail,SignUpPass,SignUpName,SignUpLastName,SignUpDateOfBirth;
Button SignUpButton;
ImageView GoBackArrow;
private FirebaseAuth auth;
final Calendar myCalendar = Calendar.getInstance();
private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
FirebaseFirestore db = FirebaseFirestore.getInstance();



    @SuppressLint({"ClickableViewAccessibility", "WrongViewCast"})

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };


        Objects.requireNonNull(getSupportActionBar()).hide();
        auth = FirebaseAuth.getInstance();
        SignUpButton = findViewById(R.id.create_Account);
        SignUpMail = findViewById(R.id.email_adress_editbox);
        SignUpPass = findViewById(R.id.password_editbox);
        SignUpName = findViewById(R.id.first_name_editbox);
        SignUpLastName = findViewById(R.id.last_name_editbox);
        SignUpDateOfBirth = findViewById(R.id.date_editbox);
        GoBackArrow = findViewById(R.id.go_back_arrow);


        GoBackArrow.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            startActivity(new Intent(Sign_Up.this, LoginScreen.class));
            overridePendingTransition(R.anim.push_down_out,R.anim.push_down_in);
            finish();
        });

        SignUpMail.setOnTouchListener((view, motionEvent) -> {
            SignUpMail.setBackgroundResource(R.drawable.border_black);
            SignUpName.setBackgroundResource(R.drawable.border);
            SignUpLastName.setBackgroundResource(R.drawable.border);
            SignUpPass.setBackgroundResource(R.drawable.border);
            SignUpDateOfBirth.setBackgroundResource(R.drawable.border);

            return false;
        });

        SignUpPass.setOnTouchListener((view, motionEvent) -> {
            SignUpMail.setBackgroundResource(R.drawable.border);
            SignUpName.setBackgroundResource(R.drawable.border);
            SignUpLastName.setBackgroundResource(R.drawable.border);
            SignUpPass.setBackgroundResource(R.drawable.border_black);
            SignUpDateOfBirth.setBackgroundResource(R.drawable.border);
            return false;
        });

        SignUpName.setOnTouchListener((view, motionEvent) -> {
            SignUpMail.setBackgroundResource(R.drawable.border);
            SignUpName.setBackgroundResource(R.drawable.border_black);
            SignUpLastName.setBackgroundResource(R.drawable.border);
            SignUpPass.setBackgroundResource(R.drawable.border);
            SignUpDateOfBirth.setBackgroundResource(R.drawable.border);
            return false;
        });

        SignUpLastName.setOnTouchListener((view, motionEvent) -> {
            SignUpMail.setBackgroundResource(R.drawable.border);
            SignUpName.setBackgroundResource(R.drawable.border);
            SignUpLastName.setBackgroundResource(R.drawable.border_black);
            SignUpPass.setBackgroundResource(R.drawable.border);
            SignUpDateOfBirth.setBackgroundResource(R.drawable.border);
            return false;
        });

        SignUpDateOfBirth.setOnTouchListener((view, motionEvent) -> {
            SignUpMail.setBackgroundResource(R.drawable.border);
            SignUpName.setBackgroundResource(R.drawable.border);
            SignUpLastName.setBackgroundResource(R.drawable.border);
            SignUpPass.setBackgroundResource(R.drawable.border);
            SignUpDateOfBirth.setBackgroundResource(R.drawable.border_black);
            SignUpDateOfBirth.setShowSoftInputOnFocus(false);
            return false;
        });

        SignUpDateOfBirth.setOnClickListener(view -> new DatePickerDialog(Sign_Up
                .this,date,myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH))
                .show());


        // After clicking a "create account" button, it creates a new user.
        SignUpButton.setOnClickListener(view -> {

            String email = SignUpMail.getText().toString();
            String pass = SignUpPass.getText().toString();
            String name = SignUpName.getText().toString();
            String lastName = SignUpLastName.getText().toString();
            String dateOfBirth = SignUpDateOfBirth.getText().toString();



            if(TextUtils.isEmpty(email)){
                Toast.makeText(getApplicationContext(),
                        "Please enter your E-mail address",Toast.LENGTH_SHORT).show();

            }
            else if(TextUtils.isEmpty(pass)){
                Toast.makeText(getApplicationContext(),
                        "Please enter your Password",Toast.LENGTH_SHORT).show();

            }
            else if(pass.length() == 0){
                Toast.makeText(getApplicationContext(),
                        "Please enter your Password",Toast.LENGTH_SHORT).show();

            }
            else if(pass.length() < 6 ){
                Toast.makeText(getApplicationContext(),
                        "Password must have at least 6 digits",Toast.LENGTH_SHORT).show();

            }
            else if(TextUtils.isDigitsOnly(pass)){
                Toast.makeText(getApplicationContext(),
                        "Password must include letters",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(name)){
                Toast.makeText(getApplicationContext(),
                        "Please provide name",Toast.LENGTH_SHORT).show();
            }

            else if(TextUtils.isEmpty(lastName)){
                Toast.makeText(getApplicationContext(),
                        "Please provide last name",Toast.LENGTH_SHORT).show();
            }

            else if(TextUtils.isEmpty(dateOfBirth)){
                Toast.makeText(getApplicationContext(),
                        "Please provide date of birth",Toast.LENGTH_SHORT).show();
            }



            else{
                auth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener(Sign_Up.this,
                                task -> {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(Sign_Up.this, "ERROR", Toast
                                        .LENGTH_SHORT).show();
                                    }else{
                                        FirebaseUser user = auth.getCurrentUser();
                                        EmailAndPass.email = email;
                                        EmailAndPass.pass = pass;



                                        user.sendEmailVerification();

                                        Map<String, Object> user1 = new HashMap<>();
                                        user1.put("FirstName", name);
                                        user1.put("LastName", lastName);
                                        user1.put("Email", email);
                                        user1.put("DateOfBirth", dateOfBirth);
                                        db.collection("users").document(user.getUid())
                                                .set(user1);



                                            startActivity(new Intent(Sign_Up.this,
                                                    EmailVerification.class));
                                            overridePendingTransition(R.anim.slide_in,
                                                    R.anim.slide_in);

                                    }
                                });

            }
        });










    }
    private void updateLabel(){
        String myFormat = "dd/M/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        SignUpDateOfBirth.setText(dateFormat.format(myCalendar.getTime()));


    }
}