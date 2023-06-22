package com.example.mp3amp.domain.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3amp.R;
import com.example.mp3amp.domain.holder.ListHolder;
import com.example.mp3amp.domain.models.AudioModel;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    private final ArrayList<AudioModel> musicDataArrayList = new ArrayList<>();
    MediaPlayer mediaPlayer;
    private Context context;
    public ListAdapter(Context context){
        this.context = context;
    }

    public void getList(AudioModel musicData){
        musicDataArrayList.add(musicData);
        notifyDataSetChanged();
    }
    public void clearList(){
        musicDataArrayList.clear();
        notifyDataSetChanged();
    }

    @NonNull @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        holder.bind(musicDataArrayList.get(position));
        ImageView play = holder.getBinding().play;
        play.setOnClickListener(
                v -> {
                    if (play.getDrawable().getConstantState() == context.getResources().getDrawable(R.drawable.baseline_play_circle_filled_24).getConstantState()) {
                        play.setImageResource(R.drawable.baseline_pause_circle_24);
                        Log.d("MyMp3LOg", musicDataArrayList.get(position).uri.toString());
                        mediaPlayer = MediaPlayer.create(context, musicDataArrayList.get(position).uri);
                        mediaPlayer.start();
                    }
                    else {
                        play.setImageResource(R.drawable.baseline_play_circle_filled_24);
                        mediaPlayer.stop();
                        mediaPlayer.release();
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        return musicDataArrayList.size();
    }
}
