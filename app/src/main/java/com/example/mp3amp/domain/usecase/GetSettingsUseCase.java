package com.example.mp3amp.domain.usecase;

import com.example.mp3amp.domain.models.MusicSettings;
import com.example.mp3amp.domain.repository.SettingsRepository;

public class GetSettingsUseCase {
    SettingsRepository dataRepository;

    public GetSettingsUseCase(SettingsRepository dataRepository){
        this.dataRepository = dataRepository;
    }
    public MusicSettings execute(){
        return dataRepository.getSettings();
    }
}
