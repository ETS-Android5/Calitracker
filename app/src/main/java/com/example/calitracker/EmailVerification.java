package com.example.calitracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class EmailVerification extends AppCompatActivity {
Button verifiedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);
        Objects.requireNonNull(getSupportActionBar()).hide();
        verifiedButton = findViewById(R.id.verified_button);
        verifiedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmailVerification.this,
                        LogIn.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });
    }
}