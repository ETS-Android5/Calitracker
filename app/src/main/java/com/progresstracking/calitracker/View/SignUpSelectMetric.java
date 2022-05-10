package com.progresstracking.calitracker.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.progresstracking.calitracker.Model.EmailAndPass;
import com.progresstracking.calitracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpSelectMetric extends AppCompatActivity {
    private FirebaseAuth auth;
    private boolean kg_clicked = false;
    private boolean lb_clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_select_metric);
        Objects.requireNonNull(getSupportActionBar()).hide();
        TextView selectKg,selectLb,goBack,goNext,goSkip;
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();

        selectKg = findViewById(R.id.selectKg_textview);
        selectLb = findViewById(R.id.selectLb_textView);
        goBack = findViewById(R.id.back_metric_textview);
        goNext = findViewById(R.id.next_metric_textview);
        goSkip = findViewById(R.id.skip_metric_textview);

        selectKg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectKg.setBackgroundResource(R.drawable.border_black);
                selectLb.setBackgroundResource(R.drawable.border);
                kg_clicked = true;
                lb_clicked = false;
            }
        });

        selectLb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLb.setBackgroundResource(R.drawable.border_black);
                selectKg.setBackgroundResource(R.drawable.border);
                lb_clicked = true;
                kg_clicked = false;
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpSelectMetric.this,
                        SignUpSelectGender.class));
                overridePendingTransition(R.anim.slide_out, R.anim.slide_out);
            }
        });

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(kg_clicked){
                    Map<String, Object> metric = new HashMap<>();
                    metric.put("Metric", "Kg");

                    auth.signInWithEmailAndPassword(EmailAndPass.email,EmailAndPass.pass);
                    FirebaseUser user = auth.getCurrentUser();
                    DocumentReference metricRef = db.collection("users").
                            document(user.getUid());

                    metricRef.update(metric);
                }

                if(lb_clicked){
                    Map<String, Object> metric = new HashMap<>();
                    metric.put("Metric", "Lb");

                    auth.signInWithEmailAndPassword(EmailAndPass.email,EmailAndPass.pass);
                    FirebaseUser user = auth.getCurrentUser();
                    DocumentReference metricRef = db.collection("users").
                            document(user.getUid());

                    metricRef.update(metric);
                }


                startActivity(new Intent(SignUpSelectMetric.this,
                        SignUpPushNotifications.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });

        goSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpSelectMetric.this,
                        SignUpPushNotifications.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });




    }
}