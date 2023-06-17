package com.example.mp3amp.domain.usecase;

import com.example.mp3amp.data.repository.SettingsRepositoryImpl;
import com.example.mp3amp.domain.models.MusicSettings;

public class GetSettingsUseCase {
    SettingsRepositoryImpl dataRepository;

    public GetSettingsUseCase(SettingsRepositoryImpl dataRepository){
        this.dataRepository = dataRepository;
    }
    public MusicSettings execute(MusicSettings settings){
        return dataRepository.getSettings(settings);
    }
}
