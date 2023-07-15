package com.gigcreator.mp3amp.domain.models;

public class AudioModel {
    public String name;
    public String data;

    public AudioModel(String name, String data) {
        this.name = name;
        this.data = data;

    }

    public String getName() {
        return name;
    }
    public String getData(){
        return data;
    }
}
