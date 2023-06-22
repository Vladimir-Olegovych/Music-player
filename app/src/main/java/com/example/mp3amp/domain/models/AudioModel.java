package com.example.mp3amp.domain.models;

import android.net.Uri;

public class AudioModel {
    public String name;
    public String data;
    public Uri uri;

    public AudioModel(String name, String data, Uri uri) {
        this.name = name;
        this.data = data;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }
    public String getData(){
        return data;
    }
    public Uri getUri(){return uri;}
}
