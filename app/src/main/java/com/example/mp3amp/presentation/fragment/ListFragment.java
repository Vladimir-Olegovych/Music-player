package com.example.mp3amp.presentation.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mp3amp.data.repository.GetDownloadsListRepositoryImpl;
import com.example.mp3amp.databinding.FragmentListBinding;
import com.example.mp3amp.domain.adapter.ListAdapter;
import com.example.mp3amp.domain.models.AudioModel;
import com.example.mp3amp.domain.usecase.GetDownloadsListUseCase;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ListAdapter adapter;

    private GetDownloadsListRepositoryImpl dataRepository;
    private GetDownloadsListUseCase dataUseCase;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater);
        dataRepository = new GetDownloadsListRepositoryImpl(requireContext(), this.requireActivity());
        dataUseCase = new GetDownloadsListUseCase(dataRepository);
        adapter = new ListAdapter(requireContext());
        adapter.clearList();
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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.destroy();
    }
}