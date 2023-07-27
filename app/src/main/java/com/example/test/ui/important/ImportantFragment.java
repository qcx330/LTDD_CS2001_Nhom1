package com.example.test.ui.important;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.test.adapter.RcVwAdapter;
import com.example.test.databinding.FragmentImportantBinding;
import com.example.test.model.TaskModel;

import java.util.List;

public class ImportantFragment extends Fragment {

    private FragmentImportantBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ImportantViewModel homeViewModel =
                new ViewModelProvider(this).get(ImportantViewModel.class);

        binding = FragmentImportantBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        myDayViewModel.getTaskListLiveData().observe(getViewLifecycleOwner(), new Observer<List<TaskModel>>() {
//            @Override
//            public void onChanged(List<TaskModel> taskModels) {
//                try {
//                    adapter = new RcVwAdapter(taskModels);
////                    adapterTrue = new RcVwAdapter(taskModels);
//                    Thread.sleep(500);
////                    recyViewTrue.setAdapter(adapterTrue);
////                    recyViewTrue.setLayoutManager(new LinearLayoutManager(getContext()));
//                    recyView.setAdapter(adapter);
//                    recyView.setLayoutManager(new LinearLayoutManager(getContext()));
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}