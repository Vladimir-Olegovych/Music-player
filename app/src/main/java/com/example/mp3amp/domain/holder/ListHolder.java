package com.example.mp3amp.domain.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mp3amp.databinding.CardViewBinding;
import com.example.mp3amp.domain.models.AudioModel;

public class ListHolder extends RecyclerView.ViewHolder {
    CardViewBinding binding;
    public ListHolder(@NonNull View itemView) {
        super(itemView);
        binding = CardViewBinding.bind(itemView);
    }
    public void bind(AudioModel musicData){
        binding.tvName.setText(musicData.name);
        binding.tvData.setText(musicData.data);
        
    }
    public CardViewBinding getBinding(){
        return binding;
    }
}