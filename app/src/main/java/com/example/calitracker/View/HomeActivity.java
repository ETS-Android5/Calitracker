package com.example.calitracker.View;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calitracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    BottomNavigationView bottomNavigationView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
        auth = FirebaseAuth.getInstance();




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

        FirebaseUser user = auth.getCurrentUser();
        db.collection("users").document(user.getUid()).get()
        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        if(document.get("LockScreen").equals(true)) {
                            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        }else{
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                        }

                    }
                }
            }
        });


    }


}