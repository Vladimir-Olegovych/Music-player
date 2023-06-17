package com.example.mp3amp.domain.usecase;

import com.example.mp3amp.data.repository.SettingsRepositoryImpl;
import com.example.mp3amp.domain.models.MusicSettings;

public class SaveSettingUseCase {
    SettingsRepositoryImpl dataRepository;

    public SaveSettingUseCase(SettingsRepositoryImpl dataRepository){
        this.dataRepository = dataRepository;
    }
    public Boolean execute(MusicSettings settings){
        return dataRepository.saveSettings(settings);
    }
}
