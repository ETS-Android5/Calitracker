package com.example.calitracker.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.calitracker.Model.EmailAndPass;
import com.example.calitracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpPushNotifications extends AppCompatActivity {
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_push_notifications);
        Objects.requireNonNull(getSupportActionBar()).hide();
        TextView goBack,goNext,goSkip;
        Switch mySwitch;

        goBack = findViewById(R.id.back_pushnotif_textview);
        goNext = findViewById(R.id.next_pushnotif_textview);
        goSkip = findViewById(R.id.skip_pushnotif_textview);
        mySwitch = findViewById(R.id.push_notif_switch);
        auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpPushNotifications.this,
                        SignUpSelectMetric.class));
                overridePendingTransition(R.anim.slide_out, R.anim.slide_out);
            }
        });


        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpPushNotifications.this,
                        SignUpEnd.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });


        goSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpPushNotifications.this,
                        SignUpEnd.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });
        String topic = "PushNotifications";
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
        Log.d("subscribed", "subscribed: no ");


        Map<String, Object> pushNotif = new HashMap<>();
        pushNotif.put("Notifications", false);


        auth.signInWithEmailAndPassword(EmailAndPass.email,EmailAndPass.pass);
        FirebaseUser user = auth.getCurrentUser();
        DocumentReference pushRef = db.collection("users").
                document(user.getUid());

        pushRef.update(pushNotif);



        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String topic = "PushNotifications";

                if(isChecked) {

                    FirebaseMessaging.getInstance().subscribeToTopic(topic);
                    Log.d("subscribed", "subscribed: yes ");


                   Map<String, Object> pushNotif = new HashMap<>();
                    pushNotif.put("Notifications", true);

                    auth.signInWithEmailAndPassword(EmailAndPass.email, EmailAndPass.pass);
                    FirebaseUser user = auth.getCurrentUser();
                    DocumentReference pushRef = db.collection("users").
                            document(user.getUid());

                    pushRef.update(pushNotif);
                }else{

                    FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                    Log.d("subscribed", "subscribed: no ");


                    Map<String, Object> pushNotif = new HashMap<>();
                    pushNotif.put("Notifications", false);


                    auth.signInWithEmailAndPassword(EmailAndPass.email,EmailAndPass.pass);
                    FirebaseUser user = auth.getCurrentUser();
                    DocumentReference pushRef = db.collection("users").
                            document(user.getUid());

                    pushRef.update(pushNotif);
                }

            }
        });



    }
}