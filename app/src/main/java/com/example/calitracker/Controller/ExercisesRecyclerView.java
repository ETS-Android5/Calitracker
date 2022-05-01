package com.example.calitracker.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calitracker.R;

public class ExercisesRecyclerView extends RecyclerView.Adapter<ExercisesRecyclerView.MyViewHolder>
{
    String[] data1, data2;
    int[] images;
    Context context;

        public ExercisesRecyclerView(Context ct, String[] s1, String[] s2, int[] img) {
            context = ct;
            data1 = s1;
            data2 = s2;
            images = img;
        }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercises_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.level.setText(data1[position]);
        holder.description.setText(data2[position]);

    }

    @Override
    public int getItemCount() {
        return data2.length;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView level, description;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            level = itemView.findViewById(R.id.exercisesLevelTextView);
            description = itemView.findViewById(R.id.exercisesNameTextView);

        }
    }
}
