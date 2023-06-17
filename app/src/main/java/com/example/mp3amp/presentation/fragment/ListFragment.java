package com.example.mp3amp.presentation.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp3amp.R;
import com.example.mp3amp.databinding.FragmentListBinding;
import com.example.mp3amp.domain.adapter.ListAdapter;
import com.example.mp3amp.domain.models.MusicData;

public class ListFragment extends Fragment {
    private FragmentListBinding binding;
    private ListAdapter adapter = new ListAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater);
        adapter.clearList();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rcView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rcView.setAdapter(adapter);

        adapter.getList(new MusicData("loxS"));
        adapter.getList(new MusicData("loxSsadasd"));
    }
}