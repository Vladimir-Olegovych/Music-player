package com.example.mp3amp.domain.repository;

import com.example.mp3amp.domain.models.MusicSettings;

public interface SettingsRepository {

    Boolean saveSettings(MusicSettings settings);

    MusicSettings getSettings();
}
