package com.progresstracking.calitracker.View;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.progresstracking.calitracker.Controller.recyclerViewAdapter;
import com.progresstracking.calitracker.R;


public class HomeFragment extends Fragment {

    String[] s1, s2;
    int[] images = {R.drawable.squat,R.drawable.pull_up,R.drawable.handstand,R.drawable.leg_raises,
    R.drawable.push_up,R.drawable.dips,R.drawable.horizontal_pull,R.drawable.plank};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        ImageView imageView = (ImageView)view.findViewById(R.id.infoImageView);
        FrameLayout frameLayout = (FrameLayout)view.findViewById(R.id.homeFrameLayout);
        LayoutInflater inflater1 = getLayoutInflater();
        View myLayout = inflater1.inflate(R.layout.info_layout, container, false);

        s1 = getResources().getStringArray(R.array.exercises);
        s2 = getResources().getStringArray(R.array.description);

        recyclerViewAdapter recyclerViewAdapter = new recyclerViewAdapter(getActivity(),
                s1,s2,images);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogBuilder;
                AlertDialog dialog;
                dialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                View view2 = inflater.inflate(R.layout.info_layout,null);
                dialogBuilder.setView(view2);
                dialog = dialogBuilder.create();
                dialog.show();
            }
        });






        // Inflate the layout for this fragment
        return view;
    }
}