package com.gigcreator.domain.repository;

public interface PlayMusicRepository {

    void play(String data);
    void seekPlayer(int progress);
    void stop();
    void clear();
    void pause();
    void resume();

    int getDuration();
    int getCurrentPosition();
}
