package com.example.mp3amp.domain.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3amp.R;
import com.example.mp3amp.domain.holder.ListHolder;
import com.example.mp3amp.domain.models.AudioModel;
import com.example.mp3amp.domain.music.PlayMusic;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    private final PlayMusic playMusic;
    private final Context context;
    private MediaPlayer mediaPlayer;

    public ListAdapter(Context context){
        this.context = context;
        playMusic = new PlayMusic(context);
    }

    private final ArrayList<AudioModel> musicDataArrayList = new ArrayList<>();

    public void getList(AudioModel musicData){
        musicDataArrayList.add(musicData);
        notifyDataSetChanged();
    }
    public void clearList(){
        musicDataArrayList.clear();
        notifyDataSetChanged();
    }
    public void destroy(){
        if(mediaPlayer != null) mediaPlayer.release();
        mediaPlayer = null;
    }

    @NonNull @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ListHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.bind(musicDataArrayList.get(position));
        ImageView play = holder.getBinding().play;
        String data = musicDataArrayList.get(position).data;

        play.setOnClickListener(
            v -> {
                if (play.getDrawable().getConstantState() ==
                        context.getResources().
                                getDrawable(R.drawable.baseline_play_circle_filled_24).
                                getConstantState()) {
                    if (playMusic.getPosition() != -1 && position != playMusic.getPosition()){
                        playMusic.getPLay().setImageResource(R.drawable.baseline_play_circle_filled_24);
                        mediaPlayer = playMusic.stop(mediaPlayer);
                    }
                    play.setImageResource(R.drawable.baseline_pause_circle_24);
                    mediaPlayer = playMusic.play(data, position, play, mediaPlayer);
                }
                else {
                    play.setImageResource(R.drawable.baseline_play_circle_filled_24);
                    mediaPlayer = playMusic.stop(mediaPlayer);
                }
            }
        );
    }

    @Override
    public int getItemCount() {
        return musicDataArrayList.size();
    }
}
