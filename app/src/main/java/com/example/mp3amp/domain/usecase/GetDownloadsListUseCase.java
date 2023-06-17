package com.example.mp3amp.domain.usecase;


import com.example.mp3amp.data.repository.GetDownloadsListRepositoryImpl;
import com.example.mp3amp.domain.models.MusicData;

public class GetDownloadsListUseCase {
    GetDownloadsListRepositoryImpl dataRepository;

    public GetDownloadsListUseCase(GetDownloadsListRepositoryImpl dataRepository){
        this.dataRepository = dataRepository;
    }
    public MusicData execute(MusicData data){
        return dataRepository.getData(data);
    }
}
