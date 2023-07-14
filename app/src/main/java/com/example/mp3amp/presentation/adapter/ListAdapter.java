package com.example.mp3amp.presentation.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3amp.R;
import com.example.mp3amp.data.repository.PlayMusicRepositoryImpl;
import com.example.mp3amp.databinding.CardViewBinding;
import com.example.mp3amp.domain.models.AudioModel;
import com.example.mp3amp.presentation.holder.ListHolder;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    private final Context context;
    private final PlayMusicRepositoryImpl dataRepository = new PlayMusicRepositoryImpl();
    private final ArrayList<AudioModel> musicDataArrayList = new ArrayList<>();
    private ImageView play;
    public String data;



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

    public void destroy(){
        dataRepository.clear();
    }

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

        holder.getBinding().play.setOnClickListener(
                v -> {
                    if (play.getTag() == null || (Integer) play.getTag() == R.drawable.baseline_play_circle_filled_24) {

                        if (this.play != null && this.play.getTag() != play.getTag()) {

                            dataRepository.stop();
                            setResource(R.drawable.baseline_play_circle_filled_24, this.play);
                        }

                        this.play = play;
                        setResource(R.drawable.baseline_pause_circle_24, play);

                        dataRepository.play(data, context);

                    }else {
                        setResource(R.drawable.baseline_play_circle_filled_24, play);
                        dataRepository.stop();
                    }
                }
        );
    }

    private void setResource(int resource, ImageView image){
        image.setTag(resource);
        image.setImageResource(resource);
    }
}
