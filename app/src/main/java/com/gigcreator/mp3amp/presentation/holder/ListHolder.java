package com.gigcreator.mp3amp.presentation.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gigcreator.domain.models.AudioModel;
import com.gigcreator.mp3amp.databinding.CardViewBinding;

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

    public CardViewBinding getCardView() {
        return binding;
    }
}