package com.example.mp3amp.data.repository;

import com.example.mp3amp.domain.models.MusicSettings;

public class SettingsRepositoryImpl {

    public Boolean saveSettings(MusicSettings settings){

        return true;
    }
    public MusicSettings getSettings(MusicSettings settings){

        return new MusicSettings(60);
    }

}
