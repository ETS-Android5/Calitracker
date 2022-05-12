package com.cuyer.calitracker.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cuyer.calitracker.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class CalculatorsFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculators, container, false);

            BottomNavigationView bottomNavigationView = (BottomNavigationView) view
                    .findViewById(R.id.calculatorNav_view);
            BmiFragment bmiFragment = new BmiFragment();
            BmrFragment bmrFragment = new BmrFragment();



        getChildFragmentManager().beginTransaction().replace(R.id.container_cal, bmiFragment)
                .commit();




        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView
                .OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_bmi:
                        getChildFragmentManager().beginTransaction().replace(R.id.container_cal,
                                bmiFragment )
                                .commit();
                        return true;
                    case R.id.navigation_bmr:
                        getChildFragmentManager().beginTransaction().replace(R.id.container_cal,
                                bmrFragment)
                                .commit();
                        return true;
                }

                return false;
            }
        });







                return view;
    }

}