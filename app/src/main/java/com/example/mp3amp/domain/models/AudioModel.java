package com.example.mp3amp.domain.models;

import android.net.Uri;

public class AudioModel {
    public String name;
    public int size;

    public AudioModel(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName(){
        return name;
    }


    public int getSize(){
        return size;
    }
}
