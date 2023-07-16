package com.gigcreator.mp3amp.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigcreator.mp3amp.R;
import com.gigcreator.mp3amp.data.repository.PlayMusicRepositoryImpl;
import com.gigcreator.mp3amp.domain.models.AudioModel;
import com.gigcreator.mp3amp.presentation.holder.ListHolder;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    private final PlayMusicRepositoryImpl dataRepository;
    private final ArrayList<AudioModel> musicDataArrayList = new ArrayList<>();
    private ImageView play;
    private String data = "null";
    private Boolean pause = true;



    public ListAdapter(Context context){
        dataRepository = new PlayMusicRepositoryImpl(context);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getList(AudioModel musicData){
        musicDataArrayList.add(musicData);
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void clearList(){
        musicDataArrayList.clear();
        notifyDataSetChanged();
    }

    public void destroy(){ dataRepository.clear(); }

    @Override
    public int getItemCount() {
        return musicDataArrayList.size();
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
        String data = musicDataArrayList.get(position).data;
        ImageView play = holder.getBinding().play;
        ImageView restart = holder.getBinding().restart;

        if (this.data.equals(data)){
            setResource(R.drawable.baseline_pause_circle_24, play);
        } else {
            setResource(R.drawable.baseline_play_circle_24, play);
        }
        play.setOnClickListener(
                v -> {
                    if (play.getTag().equals(R.drawable.baseline_play_circle_24)) {
                        if (this.data.equals("null") && this.play == null){
                            //start
                            pause = true;
                            playMusic(data, play);
                        }
                        if (this.data.equals(data) && this.play.getTag().equals(R.drawable.baseline_play_circle_24) ){
                            //resume
                            pause = true;
                            setResource(R.drawable.baseline_pause_circle_24, this.play);
                            dataRepository.resume();
                        }
                        if (!this.data.equals(data) && play.getTag().equals(R.drawable.baseline_play_circle_24)){
                            //reset
                            pause = true;
                            dataRepository.stop();
                            setResource(R.drawable.baseline_play_circle_24, this.play);
                            playMusic(data, play);
                        }
                    }else {
                        //pause
                        pause = false;
                        setResource(R.drawable.baseline_play_circle_24, play);
                        dataRepository.pause();
                    }
                }
        );
        restart.setOnClickListener(
                v-> {
                    if (this.data.equals(data) && pause.equals(true)){
                        dataRepository.stop();
                        dataRepository.play(this.data);
                    }
                }
        );
    }

    private void setResource(int resource, ImageView image){
        image.setTag(resource);
        image.setImageResource(resource);
    }
    private void playMusic(String data, ImageView play){
        this.data = data;
        this.play = play;
        setResource(R.drawable.baseline_pause_circle_24, play);
        dataRepository.play(data);
    }
}
