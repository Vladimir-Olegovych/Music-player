package com.example.mp3amp.data.repository;

import com.example.mp3amp.domain.models.MusicSettings;
import com.example.mp3amp.domain.repository.SettingsRepository;

public class SettingsRepositoryImpl implements SettingsRepository {

    @Override
    public Boolean saveSettings(MusicSettings settings) {
        return true;
    }

    @Override
    public MusicSettings getSettings() {
        return null;
    }

}
