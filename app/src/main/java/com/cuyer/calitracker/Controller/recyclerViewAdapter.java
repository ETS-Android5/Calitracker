package com.cuyer.calitracker.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.cuyer.calitracker.R;
import com.cuyer.calitracker.View.exercises;

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewAdapter.ViewHolder> {

    String[] data1, data2;
    int[] images;
    Context context;


    public recyclerViewAdapter(Context ct, String[] s1, String[] s2, int[] img){
    context = ct;
    data1 = s1;
    data2 = s2;
    images = img;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myImage.setImageResource(images[position]);


        holder.exercisesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, exercises.class);
                intent.putExtra("data1", data1[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView myText1;
        ImageView myImage;
        ConstraintLayout exercisesLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.exerciseTextView);
            myImage = itemView.findViewById(R.id.exerciseImageView);
            exercisesLayout = itemView.findViewById(R.id.exercisesLayout);
        }
    }
}
