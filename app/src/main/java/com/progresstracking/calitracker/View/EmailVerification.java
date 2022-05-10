package com.progresstracking.calitracker.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.progresstracking.calitracker.Model.EmailAndPass;
import com.progresstracking.calitracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class EmailVerification extends AppCompatActivity {
Button verifiedButton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        Objects.requireNonNull(getSupportActionBar()).hide();
        verifiedButton = findViewById(R.id.verified_button);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        verifiedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    mAuth.signInWithEmailAndPassword(EmailAndPass.email,EmailAndPass.pass);
                if(user.isEmailVerified()) {
                    startActivity(new Intent(EmailVerification.this,
                            SignUpSelectGender.class));
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
                }else{
                    Toast.makeText(getApplicationContext(),"Please verify your email",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}