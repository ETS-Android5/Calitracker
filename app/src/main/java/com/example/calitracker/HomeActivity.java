package com.example.calitracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Objects;
import java.util.Timer;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    ProgressFragment progressFragment = new ProgressFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    TimerFragment timerFragment = new TimerFragment();
    CalculatorsFragment calculatorsFragment = new CalculatorsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).hide();


        bottomNavigationView = findViewById(R.id.bottomNav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment)
                .commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView
                .OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                homeFragment)
                                .commit();
                        return true;
                    case R.id.navigation_calculators:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                calculatorsFragment)
                                .commit();
                        return true;
                    case R.id.navigation_progress:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                progressFragment)
                                .commit();
                        return true;
                    case R.id.navigation_settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                settingsFragment)
                                .commit();
                        return true;
                    case R.id.navigation_timer:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,
                                timerFragment)
                                .commit();
                        return true;
                }

                return false;
            }
        });



    }


}