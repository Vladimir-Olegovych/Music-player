package com.gigcreator.mp3amp.presentation.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.gigcreator.mp3amp.R;
import com.gigcreator.mp3amp.data.repository.GetDownloadsListRepositoryImpl;
import com.gigcreator.mp3amp.data.repository.PlayMusicRepositoryImpl;
import com.gigcreator.mp3amp.databinding.FragmentListBinding;
import com.gigcreator.mp3amp.domain.models.AudioModel;
import com.gigcreator.mp3amp.presentation.adapter.ListAdapter;

import java.util.ArrayList;

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

    private void onClickButton(){
        if (binding.buttonPlay.getTag() != null && binding.buttonPlay.getTag().equals(R.drawable.baseline_pause_circle_24)){
            mediaPlayer.pause();
            setTagButton(R.drawable.baseline_play_circle_24);
        }
        else {
            mediaPlayer.resume();
            setTagButton(R.drawable.baseline_pause_circle_24);
        }
    }

    public void onClickListener(String data, String name, Bitmap bitmap) {
        mediaPlayer.stop();
        mediaPlayer.play(data);
        initialiseSeekBar();
        binding.musicName.setText(name);
        binding.imageArtist.setImageBitmap(bitmap);
        setTagButton(R.drawable.baseline_pause_circle_24);
    }

    private String getTime(final int milliSex){

        final StringBuilder builder = new StringBuilder();
        int seconds = (milliSex/1000) % 60;
        int minutes = (milliSex/ (1000*60)) % 60;

        if (minutes < 10) builder.append('0');
        builder.append(minutes);
        builder.append(':');
        if (seconds < 10) builder.append('0');
        builder.append(seconds);

        return builder.toString();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        ArrayList<AudioModel> musicList = dataRepository.getData();
        for (AudioModel it : musicList){
            adapter.getList(it);
        }
        try {
            AudioModel music = musicList.get(0);
            binding.musicName.setText(music.name);
            mediaPlayer.play(music.data);
            mediaPlayer.pause();
            initialiseSeekBar();
        }catch (Throwable e){
            e.printStackTrace();
        }

        binding.buttonPlay.setOnClickListener(v -> onClickButton());
        binding.imageArtist.setOnClickListener(v -> onClickButton());
        binding.buttonStop.setOnClickListener(v -> mediaPlayer.seekPlayer(0));

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) mediaPlayer.seekPlayer(progress);
                final String duration = getTime(mediaPlayer.getDuration());
                binding.textTime.setText(getTime(progress) + " of " + duration);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    @SuppressLint("SetTextI18n")
    private void initialiseSeekBar(){
        binding.seekBar.setMax(mediaPlayer.getDuration());
        final String duration = getTime(mediaPlayer.getDuration());
        binding.textTime.setText("00:00" + " of " + duration);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    binding.seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this, 1000);
                }catch (Exception e){
                    binding.seekBar.setProgress(0);
                }
            }
        }, 0);
    }
}