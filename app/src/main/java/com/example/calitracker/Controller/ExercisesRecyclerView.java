package com.example.calitracker.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calitracker.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class ExercisesRecyclerView extends RecyclerView.Adapter<ExercisesRecyclerView.MyViewHolder>
{
    String[] data1, data2, videoURL;
    int[] images;
    Context context;

        public ExercisesRecyclerView(Context ct, String[] s1, String[] s2, String[] videoArray) {
            context = ct;
            data1 = s1;
            data2 = s2;
            videoURL = videoArray;
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
        holder.playVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YouTubePlayerView youTubePlayerView;
                AlertDialog.Builder dialogBuilder;
                AlertDialog dialog;
                dialogBuilder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view2 = inflater.inflate(R.layout.popup,null);
                youTubePlayerView = view2.findViewById(R.id.youtube_player_view);
                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoID = videoURL[position];
                        youTubePlayer.loadVideo(videoID,0);
                        super.onReady(youTubePlayer);
                    }
                });



                dialogBuilder.setView(view2);
                dialog = dialogBuilder.create();
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return data2.length;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView level, description;
            ImageView playVideoButton;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            level = itemView.findViewById(R.id.exercisesLevelTextView);
            description = itemView.findViewById(R.id.exercisesNameTextView);
            playVideoButton = itemView.findViewById(R.id.playVideoButton);

        }
    }
}
