package com.example.calitracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.calitracker.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Sign_Up extends AppCompatActivity {
EditText SignUpMail,SignUpPass,SignUpName,SignUpLastName,SignUpDateOfBirth;
Button SignUpButton;
ImageView GoBackArrow;
private FirebaseAuth auth;



    @SuppressLint({"ClickableViewAccessibility", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Objects.requireNonNull(getSupportActionBar()).hide();
        auth = FirebaseAuth.getInstance();
        SignUpButton = findViewById(R.id.create_Account);
        SignUpMail = findViewById(R.id.email_adress_editbox);
        SignUpPass = findViewById(R.id.password_editbox);
        SignUpName = findViewById(R.id.first_name_editbox);
        SignUpLastName = findViewById(R.id.last_name_editbox);
        SignUpDateOfBirth = findViewById(R.id.date_editbox);
        GoBackArrow = findViewById(R.id.go_back_arrow);

        GoBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Sign_Up.this, LoginScreen.class));
                overridePendingTransition(R.anim.push_down_out,R.anim.push_down_in);
                finish();
            }
        });

        SignUpMail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpMail.setBackgroundResource(R.drawable.border_black);
                SignUpName.setBackgroundResource(R.drawable.border);
                SignUpLastName.setBackgroundResource(R.drawable.border);
                SignUpPass.setBackgroundResource(R.drawable.border);
                SignUpDateOfBirth.setBackgroundResource(R.drawable.border);

                return false;
            }
        });

        SignUpPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpMail.setBackgroundResource(R.drawable.border);
                SignUpName.setBackgroundResource(R.drawable.border);
                SignUpLastName.setBackgroundResource(R.drawable.border);
                SignUpPass.setBackgroundResource(R.drawable.border_black);
                SignUpDateOfBirth.setBackgroundResource(R.drawable.border);
                return false;
            }
        });

        SignUpName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpMail.setBackgroundResource(R.drawable.border);
                SignUpName.setBackgroundResource(R.drawable.border_black);
                SignUpLastName.setBackgroundResource(R.drawable.border);
                SignUpPass.setBackgroundResource(R.drawable.border);
                SignUpDateOfBirth.setBackgroundResource(R.drawable.border);
                return false;
            }
        });

        SignUpLastName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpMail.setBackgroundResource(R.drawable.border);
                SignUpName.setBackgroundResource(R.drawable.border);
                SignUpLastName.setBackgroundResource(R.drawable.border_black);
                SignUpPass.setBackgroundResource(R.drawable.border);
                SignUpDateOfBirth.setBackgroundResource(R.drawable.border);
                return false;
            }
        });

        SignUpDateOfBirth.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SignUpMail.setBackgroundResource(R.drawable.border);
                SignUpName.setBackgroundResource(R.drawable.border);
                SignUpLastName.setBackgroundResource(R.drawable.border);
                SignUpPass.setBackgroundResource(R.drawable.border);
                SignUpDateOfBirth.setBackgroundResource(R.drawable.border_black);
                return false;
            }
        });



        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override

            // After clicking a "create account" button, it creates a new user.
            public void onClick(View view) {

                String email = SignUpMail.getText().toString();
                String pass = SignUpPass.getText().toString();
                String name = SignUpName.getText().toString();
                String lastName = SignUpLastName.getText().toString();
                String dateOfBirth = SignUpDateOfBirth.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),
                            "Please enter your E-mail address",Toast.LENGTH_LONG).show();

                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Password",Toast.LENGTH_LONG).show();

                }
                if(pass.length() == 0){
                    Toast.makeText(getApplicationContext(),
                            "Please enter your Password",Toast.LENGTH_LONG).show();

                }
                if(pass.length() < 8 ){
                    Toast.makeText(getApplicationContext(),
                            "Password must be longer than 8 digits",Toast.LENGTH_LONG).show();

                }else{
                    auth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(Sign_Up.this,
                                    new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()){
                                        Toast.makeText(Sign_Up.this, "ERROR", Toast
                                        .LENGTH_LONG).show();
                                    }else{
                                        startActivity(new Intent(Sign_Up.this,
                                                HomeActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
            }






        });








    }

}