package com.example.mp3amp.domain.music;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class PlayMusic {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private final Context context;
    private String data;
    private ImageView play;
    private int position;

    public void stop(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public String getData(){
        return data;
    }

    public int getPosition(){
        return position;
    }

    public ImageView getPLay(){
        return play;
    }

    public PlayMusic(Context context){
        this.context = context;
    }

    public void play(String data, int position, ImageView play){
        this.data = data;
        this.position = position;
        this.play = play;
        Uri myUri = Uri.fromFile(new File(data));
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(context, myUri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        mediaPlayer.getCurrentPosition();
        mediaPlayer.start();
    }
}
