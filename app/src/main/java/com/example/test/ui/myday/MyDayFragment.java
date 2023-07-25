package com.example.test.ui.myday;

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
import androidx.recyclerview.widget.RecyclerView;
import com.example.test.adapter.RcVwAdapter;
import com.example.test.databinding.FragmentMydayBinding;

import com.example.test.model.TaskModel;

import java.util.List;

public class MyDayFragment extends Fragment {

    private FragmentMydayBinding binding;
    private RecyclerView recyView, recyViewTrue;
    private RcVwAdapter adapter, adapterTrue;
    MyDayViewModel myDayViewModel = new MyDayViewModel();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myDayViewModel = new ViewModelProvider(this).get(MyDayViewModel.class);
        binding = FragmentMydayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGreat;
        myDayViewModel.getTxtGrate().observe(getViewLifecycleOwner(), textView::setText);

        final TextView textView1 = binding.txtTime;
        myDayViewModel.getTxtTime().observe(getViewLifecycleOwner(), textView1::setText);
        recyView = binding.recycleView;
//        recyViewTrue = binding.recycleViewTrue;
        myDayViewModel.getTaskListLiveData().observe(getViewLifecycleOwner(), new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> taskModels) {
                try {
                    adapter = new RcVwAdapter(taskModels);
//                    adapterTrue = new RcVwAdapter(taskModels);
                    Thread.sleep(500);
//                    recyViewTrue.setAdapter(adapterTrue);
//                    recyViewTrue.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyView.setAdapter(adapter);
                    recyView.setLayoutManager(new LinearLayoutManager(getContext()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return root;
    }
}