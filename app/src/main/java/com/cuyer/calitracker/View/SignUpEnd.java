package com.cuyer.calitracker.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cuyer.calitracker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignUpEnd extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_end);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Button getStarted;
        auth = FirebaseAuth.getInstance();

        getStarted = findViewById(R.id.get_Started_Button);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> lockScreen = new HashMap<>();
                lockScreen.put("LockScreen", false);

                FirebaseUser user = auth.getCurrentUser();
                DocumentReference pushRef = db.collection("users").
                        document(user.getUid());

                pushRef.update(lockScreen);




                startActivity(new Intent(SignUpEnd.this,
                        HomeActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
            }
        });
    }
}