package com.gigcreator.mp3amp.domain.models;

import android.graphics.Bitmap;

public class AudioModel {

    public String name;
    public String data;
    public Bitmap bitmap;

    public AudioModel(String name, String data, Bitmap bitmap) {
        this.name = name;
        this.data = data;
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public String getData(){
        return data;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }
}
