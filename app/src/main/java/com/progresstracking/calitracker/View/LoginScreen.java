package com.progresstracking.calitracker.View;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.progresstracking.calitracker.R;

import java.util.Objects;

public class LoginScreen extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_login_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();



        // sign up button listener
        Button sign_up_button = findViewById(R.id.sign_up_button);
        Button log_in_button = findViewById(R.id.log_in_button);

        sign_up_button.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            Intent intent = new Intent(LoginScreen.this, Sign_Up.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_up_in,R.anim.push_up_out);
        });

        log_in_button.setOnClickListener(view -> {
            view.startAnimation(buttonClick);
            Intent intent = new Intent(LoginScreen.this, LogIn.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in,R.anim.slide_in);
        });


        VideoView videoview = (VideoView) findViewById(R.id.videoView2);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.login_video);

        videoview.setVideoURI(uri);
        videoview.start();

        videoview.setOnCompletionListener(mediaPlayer -> videoview.start());


    }


    // after coming back to login, start video again
    protected void onResume() {
        super.onResume();
        VideoView videoview = (VideoView) findViewById(R.id.videoView2);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.login_video);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnCompletionListener(mediaPlayer -> videoview.start());

    }

    @Override
    public void onBackPressed() {

    }

}