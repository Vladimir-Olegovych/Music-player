package com.gigcreator.mp3amp.domain.repository;

public interface PlayMusicRepository {
    void play(String data);
    void stop();
    void clear();
    void pause();
    void resume();
}
