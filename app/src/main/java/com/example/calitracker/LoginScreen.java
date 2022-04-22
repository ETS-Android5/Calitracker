package com.example.calitracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import java.util.Objects;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_login_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();



        VideoView videoview = (VideoView) findViewById(R.id.videoView2);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.login_video);

        videoview.setVideoURI(uri);
        videoview.start();

        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });


    }

    // after coming back to login, start video again
    protected void onResume() {
        super.onResume();
        VideoView videoview = (VideoView) findViewById(R.id.videoView2);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.login_video);
        videoview.setVideoURI(uri);
        videoview.start();
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoview.start();
            }
        });

    }
}