package com.example.mp3amp.data.repository;

import android.content.Context;
import android.os.Environment;

import com.example.mp3amp.domain.models.AudioModel;
import com.example.mp3amp.domain.repository.GetDownloadsListRepository;
import java.util.ArrayList;

public class GetDownloadsListRepositoryImpl implements GetDownloadsListRepository {
    private final Context context;
    public GetDownloadsListRepositoryImpl(Context context){
        this.context = context;
    }

    @Override
    public ArrayList<AudioModel> getData() {
        ArrayList<AudioModel> list = new ArrayList<>();
        //get info
        return list;
    }
}
