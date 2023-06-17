package com.example.mp3amp.domain.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3amp.databinding.CardViewBinding;
import com.example.mp3amp.domain.models.MusicData;

public class ListHolder extends RecyclerView.ViewHolder {
    CardViewBinding binding;
    public ListHolder(@NonNull View itemView) {
        super(itemView);
        binding = CardViewBinding.bind(itemView);
    }
    public void bind(MusicData musicData){
        binding.tvName.setText(musicData.name);
    }
}