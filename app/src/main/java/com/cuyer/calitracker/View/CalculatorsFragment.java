package com.cuyer.calitracker.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cuyer.calitracker.BmiImperial;
import com.cuyer.calitracker.BmrImperial;
import com.cuyer.calitracker.Model.EmailAndPass;
import com.cuyer.calitracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class CalculatorsFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculators, container, false);

            BottomNavigationView bottomNavigationView = (BottomNavigationView) view
                    .findViewById(R.id.calculatorNav_view);
            BmiFragment bmiFragment = new BmiFragment();
            BmrFragment bmrFragment = new BmrFragment();
            BmiImperial bmiImperial = new BmiImperial();
            BmrImperial bmrImperial = new BmrImperial();



        getChildFragmentManager().beginTransaction().replace(R.id.container_cal, bmiFragment)
                .commit();


        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();


        db.collection("users").document(user.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                Log.d("data", "DocumentSnapshot data: " + document.getData());

                                EmailAndPass.metric = document.get("Metric").toString();


                                if(EmailAndPass.metric.equals("Lb")){
                                    getChildFragmentManager().beginTransaction().replace(R.id.container_cal, bmiImperial)
                                            .commit();

                                    bottomNavigationView.setOnItemSelectedListener(new NavigationBarView
                                            .OnItemSelectedListener() {
                                        @Override
                                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                                            switch (item.getItemId()){
                                                case R.id.navigation_bmi:
                                                    getChildFragmentManager().beginTransaction().replace(R.id.container_cal,
                                                                    bmiImperial )
                                                            .commit();
                                                    return true;
                                                case R.id.navigation_bmr:
                                                    getChildFragmentManager().beginTransaction().replace(R.id.container_cal,
                                                                    bmrImperial)
                                                            .commit();
                                                    return true;
                                            }

                                            return false;
                                        }
                                    });

                                }else {

                                    bottomNavigationView.setOnItemSelectedListener(new NavigationBarView
                                            .OnItemSelectedListener() {
                                        @Override
                                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                                            switch (item.getItemId()) {
                                                case R.id.navigation_bmi:
                                                    getChildFragmentManager().beginTransaction().replace(R.id.container_cal,
                                                                    bmiFragment)
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
                                }










                            }else{
                                Log.d("data", "No such document");
                            }
                        }else{
                            Log.d("data", "get failed with ", task.getException());
                        }
                    }
                });











                return view;
    }

}