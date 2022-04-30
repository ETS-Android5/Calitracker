package com.example.calitracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calitracker.Controller.recyclerViewAdapter;


public class HomeFragment extends Fragment {

    String[] s1, s2;
    int[] images = {R.drawable.squat,R.drawable.pull_up,R.drawable.handstand,R.drawable.leg_raises,
    R.drawable.push_up,R.drawable.dips,R.drawable.horizontal_pull,R.drawable.plank};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.exercises);
        s2 = getResources().getStringArray(R.array.description);

        recyclerViewAdapter recyclerViewAdapter = new recyclerViewAdapter(getActivity(),
                s1,s2,images);

        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));






        // Inflate the layout for this fragment
        return view;
    }
}