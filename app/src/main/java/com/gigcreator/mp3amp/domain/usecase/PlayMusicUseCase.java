package com.gigcreator.mp3amp.domain.usecase;


import com.gigcreator.mp3amp.domain.repository.PlayMusicRepository;

public class PlayMusicUseCase {
    PlayMusicRepository dataRepository;

    public PlayMusicUseCase(PlayMusicRepository dataRepository){
        this.dataRepository = dataRepository;
    }
}
