package com.example.mp3amp.domain.repository;

import com.example.mp3amp.domain.models.AudioModel;

import java.util.ArrayList;

public interface GetDownloadsListRepository {

    ArrayList<AudioModel> getData();
}
