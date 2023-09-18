package com.gigcreator.domain.repository;


import com.gigcreator.domain.models.AudioModel;

import java.util.ArrayList;

public interface GetDownloadsListRepository {
    ArrayList<AudioModel> getData();
}
