package com.progresstracking.calitracker.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.progresstracking.calitracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(authStateListener);




    }

    // firebase authentication
    FirebaseAuth.AuthStateListener authStateListener = firebaseAuth -> {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null){
            // if user don't have account, navigate to login screen
            new Handler().postDelayed(() -> {
                startActivity(new Intent(MainActivity.this, LoginScreen.class));
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                finish();

            }, 0);

        }
        // if user already has an account, navigate to home activity
        else if (firebaseUser != null && firebaseUser.isEmailVerified()) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            finish();
        }

        else if(firebaseUser != null && !firebaseUser.isEmailVerified()){
            Toast.makeText(MainActivity.this, "Please verify your email",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, LoginScreen.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
            finish();
        }



    };

}