package com.example.calitracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class SignUpEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_end);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Button getStarted;

        getStarted = findViewById(R.id.get_Started_Button);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpEnd.this,
                        HomeActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });
    }
}