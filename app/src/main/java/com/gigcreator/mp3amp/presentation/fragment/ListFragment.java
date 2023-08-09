package com.gigcreator.mp3amp.presentation.fragment;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigcreator.mp3amp.R;
import com.gigcreator.mp3amp.data.repository.GetDownloadsListRepositoryImpl;
import com.gigcreator.mp3amp.data.repository.PlayMusicRepositoryImpl;
import com.gigcreator.mp3amp.databinding.FragmentListBinding;
import com.gigcreator.mp3amp.domain.models.AudioModel;
import com.gigcreator.mp3amp.presentation.adapter.ListAdapter;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ListAdapter adapter;

    private GetDownloadsListRepositoryImpl dataRepository;
    private PlayMusicRepositoryImpl mediaPlayer;

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.clear();
    }

    private void setTagButton(int tag){
        binding.buttonPlay.setImageResource(tag);
        binding.buttonPlay.setTag(tag);
    }

    public void onClickListener(String data, String name, Bitmap bitmap) {
        mediaPlayer.stop();
        mediaPlayer.play(data);
        binding.musicName.setText(name);
        binding.imageArtist.setImageBitmap(bitmap);
        setTagButton(R.drawable.baseline_pause_circle_24);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater);
        dataRepository = new GetDownloadsListRepositoryImpl(requireContext(), this.requireActivity());
        mediaPlayer = new PlayMusicRepositoryImpl(requireContext());
        adapter = new ListAdapter(this);
        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rcView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rcView.setAdapter(adapter);

        for (AudioModel it : dataRepository.getData()){
            adapter.getList(it);
        }
        binding.buttonPlay.setOnClickListener(
                v -> {
                    if (binding.buttonPlay.getTag() != null && binding.buttonPlay.getTag().equals(R.drawable.baseline_pause_circle_24)){
                        mediaPlayer.pause();
                        setTagButton(R.drawable.baseline_play_circle_24);
                    }
                    else {
                        mediaPlayer.resume();
                        setTagButton(R.drawable.baseline_pause_circle_24);
                    }
                }
        );
        binding.buttonStop.setOnClickListener(
                v -> {
                    mediaPlayer.restart();
                }
        );
    }
}