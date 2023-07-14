package com.example.mp3amp.domain.usecase;

import com.example.mp3amp.domain.repository.PlayMusicRepository;

public class PlayMusicUseCase {
    PlayMusicRepository dataRepository;

    public PlayMusicUseCase(PlayMusicRepository dataRepository){
        this.dataRepository = dataRepository;
    }
}
