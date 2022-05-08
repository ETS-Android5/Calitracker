package com.example.calitracker.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calitracker.R;


public class ProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_progress, container, false);



        WeightProgressFragment weightProgressFragment = new WeightProgressFragment();

        getChildFragmentManager().beginTransaction().replace(R.id.container_progress,
                weightProgressFragment)
                .commit();



        return view;
    }
}