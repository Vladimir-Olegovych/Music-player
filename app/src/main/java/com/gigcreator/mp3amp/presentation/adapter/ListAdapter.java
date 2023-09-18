package com.gigcreator.mp3amp.presentation.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigcreator.domain.models.AudioModel;
import com.gigcreator.mp3amp.R;
import com.gigcreator.mp3amp.presentation.fragment.ListFragment;
import com.gigcreator.mp3amp.presentation.holder.ListHolder;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    private final ArrayList<AudioModel> musicDataArrayList = new ArrayList<>();
    private final ListFragment listFragment;

    public ListAdapter(ListFragment listFragment){
        this.listFragment = listFragment;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getList(AudioModel musicData){
        musicDataArrayList.add(musicData);
        notifyDataSetChanged();
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
        AudioModel audio = musicDataArrayList.get(position);
        holder.getCardView().getRoot().setOnClickListener(
                v -> listFragment.onClickListener(audio.data, audio.name, audio.bitmap)
        );
    }
}
