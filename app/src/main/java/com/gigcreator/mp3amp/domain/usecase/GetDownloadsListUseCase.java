package com.gigcreator.mp3amp.domain.usecase;

import com.gigcreator.mp3amp.domain.repository.GetDownloadsListRepository;

public class GetDownloadsListUseCase {
    GetDownloadsListRepository dataRepository;

    public GetDownloadsListUseCase(GetDownloadsListRepository dataRepository){
        this.dataRepository = dataRepository;
    }
}
