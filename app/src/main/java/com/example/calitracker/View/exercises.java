package com.example.calitracker.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.calitracker.Controller.ExercisesRecyclerView;
import com.example.calitracker.R;

import java.util.Objects;

public class exercises extends AppCompatActivity {

    String data1;
    RecyclerView recyclerView;
    String[] s1,s2,s3;
    int[] images = {R.drawable.squat,R.drawable.pull_up,R.drawable.handstand,R.drawable.leg_raises,
            R.drawable.push_up,R.drawable.dips,R.drawable.horizontal_pull,R.drawable.plank};
    ImageView goBackArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Objects.requireNonNull(getSupportActionBar()).hide();
        goBackArrow = findViewById(R.id.go_back_arrow2);
        data1 = getIntent().getStringExtra("data1");

        Log.d("IntentToExercises", "onCreate: " + data1);


        recyclerView = findViewById(R.id.exercisesRecyclerView);

        if(data1.equals("SQUATS")) {
            s1 = getResources().getStringArray(R.array.LEVEL);
            s2 = getResources().getStringArray(R.array.SQUATS);
            s3 = getResources().getStringArray(R.array.SQUATS_VIDEOURL);
            ExercisesRecyclerView exercisesRecyclerView = new ExercisesRecyclerView(this,
                    s1,s2,s3,"squat");
            recyclerView.setAdapter(exercisesRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else if(data1.equals("PULL UPS")){
            s1 = getResources().getStringArray(R.array.LEVEL);
            s2 = getResources().getStringArray(R.array.PULL_UPS);
            s3 = getResources().getStringArray(R.array.PULL_UPS_VIDEOURL);
            ExercisesRecyclerView exercisesRecyclerView = new ExercisesRecyclerView(this,
                    s1,s2,s3,"pullup");
            recyclerView.setAdapter(exercisesRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else if(data1.equals("HANDSTAND PUSH UPS")){
            s1 = getResources().getStringArray(R.array.LEVEL);
            s2 = getResources().getStringArray(R.array.HANDSTAND);
            s3 = getResources().getStringArray(R.array.HANDSTAND_VIDEOURL);
            ExercisesRecyclerView exercisesRecyclerView = new ExercisesRecyclerView(this,
                    s1,s2,s3,"handstand");
            recyclerView.setAdapter(exercisesRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else if(data1.equals("LEG RAISES")){
            s1 = getResources().getStringArray(R.array.LEVEL);
            s2 = getResources().getStringArray(R.array.LEGRAISES);
            s3 = getResources().getStringArray(R.array.LEGRAISES_VIDEOURL);
            ExercisesRecyclerView exercisesRecyclerView = new ExercisesRecyclerView(this,
                    s1,s2,s3,"legraises");
            recyclerView.setAdapter(exercisesRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else if(data1.equals("PUSH UPS")){
            s1 = getResources().getStringArray(R.array.LEVEL);
            s2 = getResources().getStringArray(R.array.PUSH_UPS);
            s3 = getResources().getStringArray(R.array.PUSH_UPS_VIDEOURL);
            ExercisesRecyclerView exercisesRecyclerView = new ExercisesRecyclerView(this,
                    s1,s2,s3,"pushups");
            recyclerView.setAdapter(exercisesRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else if(data1.equals("DIPS")){
            s1 = getResources().getStringArray(R.array.LEVEL);
            s2 = getResources().getStringArray(R.array.DIPS);
            s3 = getResources().getStringArray(R.array.DIPS_VIDEOURL);
            ExercisesRecyclerView exercisesRecyclerView = new ExercisesRecyclerView(this,
                    s1,s2,s3,"dips");
            recyclerView.setAdapter(exercisesRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else if(data1.equals("HORIZONTAL PULLS")){
            s1 = getResources().getStringArray(R.array.LEVEL);
            s2 = getResources().getStringArray(R.array.HORIZONTAL_PULLS);
            s3 = getResources().getStringArray(R.array.HORIZONTAL_PULLS_VIDEOURL);
            ExercisesRecyclerView exercisesRecyclerView = new ExercisesRecyclerView(this,
                    s1,s2,s3,"horizontalpulls");
            recyclerView.setAdapter(exercisesRecyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }





        goBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(exercises.this, HomeActivity.class));
                finish();
            }
        });


    }


}