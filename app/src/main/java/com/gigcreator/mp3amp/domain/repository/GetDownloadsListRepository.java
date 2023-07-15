package com.gigcreator.mp3amp.domain.repository;


import com.gigcreator.mp3amp.domain.models.AudioModel;

import java.util.ArrayList;

public interface GetDownloadsListRepository {
    ArrayList<AudioModel> getData();
}
