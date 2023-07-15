package com.gigcreator.mp3amp.data.repository;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;

import com.gigcreator.mp3amp.domain.repository.PlayMusicRepository;

import java.io.File;

public class PlayMusicRepositoryImpl implements PlayMusicRepository {

    private final MediaPlayer mediaPlayer = new MediaPlayer();
    private Boolean progress = false;

    @Override
    public void stop() {
        progress = false;
        mediaPlayer.stop();
        mediaPlayer.reset();
    }

    @Override
    public void clear(){
        progress = false;
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public Boolean onProgress(){
        return progress;
    }

    @Override
    public void play(String data, Context context) {
        progress = true;
        Uri myUri = Uri.fromFile(new File(data));

        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(context, myUri);
            mediaPlayer.prepare();
        }catch (Throwable e){
            e.printStackTrace();
        }
        mediaPlayer.getCurrentPosition();
        mediaPlayer.start();
    }

}
