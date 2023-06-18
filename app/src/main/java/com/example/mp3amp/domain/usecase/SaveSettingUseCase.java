package com.example.mp3amp.domain.usecase;

import com.example.mp3amp.domain.models.MusicSettings;
import com.example.mp3amp.domain.repository.SettingsRepository;

public class SaveSettingUseCase {
    SettingsRepository dataRepository;

    public SaveSettingUseCase(SettingsRepository dataRepository){
        this.dataRepository = dataRepository;
    }
    public Boolean execute(MusicSettings settings){
        return dataRepository.saveSettings(settings);
    }
}
