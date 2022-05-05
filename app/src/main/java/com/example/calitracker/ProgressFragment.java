package com.example.calitracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class ProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) view
                .findViewById(R.id.progressNav_view);

        ExercisesProgressFragment exercisesProgressFragment = new ExercisesProgressFragment();
        WeightProgressFragment weightProgressFragment = new WeightProgressFragment();

        getChildFragmentManager().beginTransaction().replace(R.id.container_progress,
                weightProgressFragment)
                .commit();



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView
                .OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_weight:
                        getChildFragmentManager().beginTransaction().replace(R.id.container_progress,
                                weightProgressFragment )
                                .commit();
                        return true;
                    case R.id.navigation_exercises:
                        getChildFragmentManager().beginTransaction().replace(R.id.container_progress,
                                exercisesProgressFragment)
                                .commit();
                        return true;




                }

                return false;
            }
        });


        return view;
    }
}