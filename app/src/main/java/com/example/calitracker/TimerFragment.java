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
    public int counter;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        TextView timerTextView = (TextView) view.findViewById(R.id.timer_textview);
        Button startButton = (Button) view.findViewById(R.id.start_timer_button);



        timerTextView.setText("00:00:00");

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        return view;
    }



}