package com.example.mp3amp.domain.usecase;

import com.example.mp3amp.domain.repository.GetDownloadsListRepository;

public class GetDownloadsListUseCase {
    GetDownloadsListRepository dataRepository;

    public GetDownloadsListUseCase(GetDownloadsListRepository dataRepository){
        this.dataRepository = dataRepository;
    }
}
