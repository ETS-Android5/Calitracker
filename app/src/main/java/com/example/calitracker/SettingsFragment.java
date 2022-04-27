package com.example.calitracker;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.calitracker.Model.EmailAndPass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SettingsFragment extends Fragment {


    private FirebaseAuth auth;
    String name;
    String email;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        TextView nameTextView = (TextView)view.findViewById(R.id.nameLastNameTextView);
        TextView emailTextView = (TextView)view.findViewById(R.id.settingsEmailTextView);
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

                        EmailAndPass.name = document.get("FirstName").toString();
                        EmailAndPass.lastName = document.get("LastName").toString();
                        EmailAndPass.email = document.get("Email").toString();
                        nameTextView.setText(EmailAndPass.name + " " + EmailAndPass.lastName);
                        emailTextView.setText(EmailAndPass.email);


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