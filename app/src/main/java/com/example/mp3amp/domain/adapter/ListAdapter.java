package com.example.mp3amp.domain.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3amp.R;
import com.example.mp3amp.domain.holder.ListHolder;
import com.example.mp3amp.domain.models.AudioModel;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    private final ArrayList<AudioModel> musicDataArrayList = new ArrayList<>();

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
    }

    @Override
    public int getItemCount() {
        return musicDataArrayList.size();
    }
}
