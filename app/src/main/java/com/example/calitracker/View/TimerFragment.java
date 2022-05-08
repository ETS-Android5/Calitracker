package com.example.calitracker.View;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.calitracker.R;

import java.util.Locale;


public class TimerFragment extends Fragment {
    static  final long START_TIME_IN_MILIS = 60000;
    boolean mTimerRunning;
    CountDownTimer mCountDownTimer;
    long mTimeLeftInMiliseconds = START_TIME_IN_MILIS;
    long mEndTime;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){

            mTimerRunning = savedInstanceState.getBoolean("timerRunning");
            mTimeLeftInMiliseconds = savedInstanceState.getLong("milisecondsLeft");
            updateCountDownText();

            if(mTimerRunning){
                mEndTime = savedInstanceState.getLong("endMilisLeft");
                mTimeLeftInMiliseconds = mEndTime - System.currentTimeMillis();
                startTimer();
            }


        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        this.view = view;

        TextView timerTextView = (TextView) view.findViewById(R.id.timer_textview);
        Button startButton = (Button) view.findViewById(R.id.start_timer_button);
        Button stopButton = (Button) view.findViewById(R.id.stop_timer_button);
        Button pauseButton = (Button) view.findViewById(R.id.pause_timer_button);



        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mTimerRunning) {
                    pauseTimer();
                }

            }
        });

        updateCountDownText();


        return view;
    }

        private void startTimer(){
                mEndTime = System.currentTimeMillis() + mTimeLeftInMiliseconds;
            mCountDownTimer = new CountDownTimer(mTimeLeftInMiliseconds, 1000) {
                @Override
                public void onTick(long l) {
                    mTimeLeftInMiliseconds = l;
                    updateCountDownText();
                }

                @Override
                public void onFinish() {
                    mTimerRunning = false;
                }
            }.start();

            mTimerRunning = true;

        }

        private void updateCountDownText(){
            int minutes = (int) mTimeLeftInMiliseconds / 60000;
            int seconds = (int) mTimeLeftInMiliseconds % 60000 / 1000;

            String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
            TextView timerTextView = (TextView) view.findViewById(R.id.timer_textview);
            timerTextView.setText(timeLeftFormatted);

        }

        private void pauseTimer(){
            TextView timerTextView = (TextView) view.findViewById(R.id.timer_textview);
            if(mTimerRunning){
                mCountDownTimer.cancel();
            }

            mTimerRunning = false;
        }

        private void resetTimer(){

            if(mTimerRunning){
                mCountDownTimer.cancel();
            }
            mTimeLeftInMiliseconds = START_TIME_IN_MILIS;
            mTimerRunning = false;
            updateCountDownText();
        }







    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("milisecondsLeft", mTimeLeftInMiliseconds);
        outState.putBoolean("timerRunning", mTimerRunning);
        outState.putLong("endMilisLeft", mEndTime);

    }


}