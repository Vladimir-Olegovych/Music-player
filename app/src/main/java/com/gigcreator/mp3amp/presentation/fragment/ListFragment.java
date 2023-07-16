package com.gigcreator.mp3amp.presentation.fragment;

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

import com.gigcreator.mp3amp.data.repository.GetDownloadsListRepositoryImpl;
import com.gigcreator.mp3amp.databinding.FragmentListBinding;
import com.gigcreator.mp3amp.domain.models.AudioModel;
import com.gigcreator.mp3amp.presentation.adapter.ListAdapter;

public class ListFragment extends Fragment {

    private FragmentListBinding binding;
    private ListAdapter adapter;

    private GetDownloadsListRepositoryImpl dataRepository;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater);
        dataRepository = new GetDownloadsListRepositoryImpl(requireContext(), this.requireActivity());
        adapter = new ListAdapter(requireContext());
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