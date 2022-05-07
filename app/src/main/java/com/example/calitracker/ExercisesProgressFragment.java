package com.example.calitracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.primitives.Ints;
import com.sambhav2358.tinydb.TinyDB;
import com.sambhav2358.tinydb.TinyDBManager;

import java.util.ArrayList;
import java.util.Arrays;

public class ExercisesProgressFragment extends Fragment {

    ArrayList<Integer> emptyList = new ArrayList<Integer>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercises_progress, container, false);
        TinyDBManager manager = TinyDB.getInstance(getActivity());
        TextView squatLevel = (TextView) view.findViewById(R.id.squatleveltextView22);
        TextView pullUpLevel = (TextView) view.findViewById(R.id.pullupleveltextView22);
        TextView handstandLevel = (TextView) view.findViewById(R.id.handstandleveltextView22);
        TextView legraisesLevel = (TextView) view.findViewById(R.id.legraisesleveltextView22);
        TextView pushupsLevel = (TextView) view.findViewById(R.id.pushupsleveltextView22);
        TextView dipsLevel = (TextView) view.findViewById(R.id.dipsleveltextView22);
        TextView horizontalLevel = (TextView) view.findViewById(R.id.horizontalpullsleveltextView22);



       squatLevel.setText("LEVEL "+ manager.getInt("squatLevel", 0));









        return view;
    }
}