package com.example.calitracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Timer;
import java.util.TimerTask;


public class TimerFragment extends Fragment {
    private CountDownTimer countDownTimer;
    private long timeLeftInMiliseconds = 60000;
    private boolean timerRunning;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        TextView timerTextView = (TextView) view.findViewById(R.id.timer_textview);
        Button startButton = (Button) view.findViewById(R.id.start_timer_button);
        Button stopButton = (Button) view.findViewById(R.id.stop_timer_button);
        Button pauseButton = (Button) view.findViewById(R.id.pause_timer_button);

       timerTextView.setText("1:00");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                countDownTimer = new CountDownTimer(timeLeftInMiliseconds, 1000) {
                    @Override
                    public void onTick(long l) {
                        timeLeftInMiliseconds = l;
                        int minutes = (int) timeLeftInMiliseconds / 60000;
                        int seconds = (int) timeLeftInMiliseconds % 60000 / 1000;
                        String timeLeftText;
                        timeLeftText = "" + minutes;
                        timeLeftText += ":";
                        if (seconds < 10) timeLeftText += "0";
                        timeLeftText += seconds;
                        timerTextView.setText(timeLeftText);

                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();

                timerRunning = true;


            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(timerRunning) {
                    countDownTimer.cancel();
                }
                timerRunning = false;

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    countDownTimer.cancel();
                    timerTextView.setText("1:00");
                    timeLeftInMiliseconds = 60000;


            }
        });

        return view;
    }

}