package com.example.mp3amp.domain.usecase;


import com.example.mp3amp.domain.models.AudioModel;
import com.example.mp3amp.domain.repository.GetDownloadsListRepository;

import java.util.ArrayList;

public class GetDownloadsListUseCase {
    GetDownloadsListRepository dataRepository;

    public GetDownloadsListUseCase(GetDownloadsListRepository dataRepository){
        this.dataRepository = dataRepository;
    }
    public ArrayList<AudioModel> execute(){
        return dataRepository.getData();
    }
}
