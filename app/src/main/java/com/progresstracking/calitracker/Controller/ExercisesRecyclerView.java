package com.progresstracking.calitracker.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.progresstracking.calitracker.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.sambhav2358.tinydb.TinyDB;
import com.sambhav2358.tinydb.TinyDBManager;

import java.util.ArrayList;

public class ExercisesRecyclerView extends RecyclerView.Adapter<ExercisesRecyclerView.MyViewHolder>
{
    String[] data1, data2, videoURL;
    String exercise;
    int[] images;
    Context context;
    TinyDBManager tinyDB;

    public ExercisesRecyclerView(Context ct, String[] s1, String[] s2, String[] videoArray,
                                 String exerciseType) {
        context = ct;
        data1 = s1;
        data2 = s2;
        videoURL = videoArray;
        exercise = exerciseType;
        tinyDB = TinyDB.getInstance(context);
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

        // function
        // 1. After the user clicks the CheckBox, save this certain checkbox index
        // 2. put saved checkbox indexes to an array list
        // 3. sort array list to find the highest index - this index will be your current level
        // 4. pass this information to the exercise_progress fragment
        // 5. in exercise_progress fragment get that information and change relevant TextView


        TinyDBManager manager2 = TinyDB.getInstance(context);
        ArrayList<Integer> squatIndexesArrayList = new ArrayList<Integer>();
        ArrayList<Integer> pullupIndexesArrayList = new ArrayList<Integer>();
        ArrayList<Integer> handstandIndexesArrayList = new ArrayList<Integer>();
        ArrayList<Integer> legraisesIndexesArrayList = new ArrayList<Integer>();
        ArrayList<Integer> pushupsIndexesArrayList = new ArrayList<Integer>();
        ArrayList<Integer> dipsIndexesArrayList = new ArrayList<Integer>();
        ArrayList<Integer> horizontalIndexesArrayList = new ArrayList<Integer>();




        // passing true if user clicked on the check button

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){

                    tinyDB.putBoolean("checkBox"+data2[position]+exercise, true);



                }else{
                    tinyDB.putBoolean("checkBox"+data2[position]+exercise, false);


                }

            }
        });





        if(manager2.getBoolean("checkBox" + data2[position] + exercise, false)) {
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }




        // series initialization

        TinyDBManager manager = TinyDB.getInstance(context);
        holder.series1.setText(manager.getString("series1exercise"+data2[position]+exercise, ""));
        holder.series2.setText(manager.getString("series2exercise"+data2[position]+exercise, ""));
        holder.series3.setText(manager.getString("series3exercise"+data2[position]+exercise, ""));
        holder.series4.setText(manager.getString("series4exercise"+data2[position]+exercise, ""));
        holder.series5.setText(manager.getString("series5exercise"+data2[position]+exercise, ""));



        // series TextWatchers
        holder.series1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //todo auto generated

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tinyDB.putString("series1exercise"+data2[position]+exercise, editable.toString());
            }
        });

        holder.series2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tinyDB.putString("series2exercise"+data2[position]+exercise, editable.toString());
            }
        });

        holder.series3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tinyDB.putString("series3exercise"+data2[position]+exercise, editable.toString());
            }
        });

        holder.series4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tinyDB.putString("series4exercise"+data2[position]+exercise, editable.toString());
            }
        });

        holder.series5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable editable) {
                tinyDB.putString("series5exercise"+data2[position]+exercise, editable.toString());
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
        EditText series1,series2,series3,series4,series5;
        CheckBox checkBox;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            level = itemView.findViewById(R.id.exercisesLevelTextView);
            description = itemView.findViewById(R.id.exercisesNameTextView);
            playVideoButton = itemView.findViewById(R.id.playVideoButton);
            series1 = itemView.findViewById(R.id.series1);
            series2 = itemView.findViewById(R.id.series2);
            series3 = itemView.findViewById(R.id.series3);
            series4 = itemView.findViewById(R.id.series4);
            series5 = itemView.findViewById(R.id.series5);
            checkBox = itemView.findViewById(R.id.exercisesCheckBox);


        }
    }
}