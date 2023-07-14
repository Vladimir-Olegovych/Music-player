package com.example.mp3amp.domain.repository;

import android.content.Context;

public interface PlayMusicRepository {
    void play(String data, Context context);
    void stop();
    void clear();
}
