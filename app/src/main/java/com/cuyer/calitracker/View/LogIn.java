package com.cuyer.calitracker.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cuyer.calitracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LogIn extends AppCompatActivity {

    ImageView GoBackArrow;
    EditText LogInMail,LogInPass;
    Button LogInButton;
    TextView forgotPassword;
    private FirebaseAuth mAuth;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Objects.requireNonNull(getSupportActionBar()).hide();
        mAuth = FirebaseAuth.getInstance();
        GoBackArrow = findViewById(R.id.go_back_arrow);
        LogInMail = findViewById(R.id.email_adress_editbox);
        LogInPass = findViewById(R.id.password_editbox);
        LogInButton = findViewById(R.id.logIntoTheApp);
        forgotPassword = findViewById(R.id.forgotPassword);

        GoBackArrow.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            startActivity(new Intent(LogIn.this, LoginScreen.class));
            overridePendingTransition(R.anim.slide_out,R.anim.slide_out);
            finish();
        });

        LogInMail.setOnTouchListener((view, motionEvent) -> {
            LogInMail.setBackgroundResource(R.drawable.border_black);
            LogInPass.setBackgroundResource(R.drawable.border);
            return false;
        });
        LogInPass.setOnTouchListener((view, motionEvent) -> {
            LogInMail.setBackgroundResource(R.drawable.border);
            LogInPass.setBackgroundResource(R.drawable.border_black);
            return false;
        });



        LogInMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(LogInMail.getText().toString()) && !TextUtils.isEmpty(LogInPass.getText().toString())) {
                    LogInButton.setBackgroundColor(Color.BLACK);// set here your backgournd to button
                    LogInButton.setTextColor(Color.WHITE);

                }else {
                    LogInButton.setBackgroundColor(Color.WHITE);
                    LogInButton.setTextColor(Color.parseColor("#494949"));

                }

            }
        });


        LogInPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (!TextUtils.isEmpty(LogInMail.getText().toString()) && !TextUtils.isEmpty(LogInPass.getText().toString())) {
                    LogInButton.setBackgroundColor(Color.BLACK);// set here your backgournd to button
                    LogInButton.setTextColor(Color.WHITE);

                }else {
                    LogInButton.setBackgroundColor(Color.WHITE);
                    LogInButton.setTextColor(Color.parseColor("#494949"));

                }

            }
        });







        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = LogInMail.getText().toString();
                String pass = LogInPass.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),
                            "Please enter your E-mail address",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),
                            "Please provide password",Toast.LENGTH_SHORT).show();
                }




                else {
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(LogIn.this,
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                FirebaseUser user = mAuth.getCurrentUser();
                                                if(user.isEmailVerified()) {
                                                    //Sign in success, go to the home activity
                                                    Log.d("tag",
                                                            "signInWithEmail:success");
                                                    startActivity(new Intent(LogIn.
                                                            this,
                                                            HomeActivity.class));
                                                    overridePendingTransition(R.anim.slide_in,
                                                            R.anim.slide_in);
                                                }

                                            } else {
                                                // If sign in fails, display a message to the user.
                                                Log.w("failed_login",
                                                        "signInWithEmail:failure",
                                                        task.getException());
                                                Toast.makeText(LogIn.this,
                                                        "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });
                }


            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = LogInMail.getText().toString();
                view.startAnimation(buttonClick);
                if(TextUtils.isEmpty(emailAddress)){
                    Toast.makeText(LogIn.this,
                            "Please provide email",
                            Toast.LENGTH_SHORT).show();
                }else {

                    mAuth.sendPasswordResetEmail(emailAddress);
                    Toast.makeText(LogIn.this,
                            "Password reset email has been sent",
                            Toast.LENGTH_SHORT).show();


                }
            }
        });
    }


    // hide keyboard after clicking outside of editbox
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


}