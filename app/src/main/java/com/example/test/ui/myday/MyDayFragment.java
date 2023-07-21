package com.example.test.ui.myday;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.adapter.RcVwAdapter;
import com.example.test.controller.task.TaskController;
import com.example.test.databinding.FragmentMydayBinding;
import com.example.test.model.TaskModel;

import java.util.ArrayList;
import java.util.List;


public class MyDayFragment extends Fragment {

    private FragmentMydayBinding binding;
    private RecyclerView recyView;
    private RecyclerView recyView1;
    private RcVwAdapter adapter;
    TaskController taskController = new TaskController();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyDayViewModel myDayViewModel =
                new ViewModelProvider(this).get(MyDayViewModel.class);
        binding = FragmentMydayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGreat;
        myDayViewModel.getTxtGrate().observe(getViewLifecycleOwner(),textView::setText);

        final TextView textView1 = binding.txtTime;
        myDayViewModel.getTxtTime().observe(getViewLifecycleOwner(),textView1::setText);

        recyView = binding.recycleView;
        recyView.setAdapter(myDayViewModel.getAdapter());
        recyView.setLayoutManager(new LinearLayoutManager(getContext()));

//        RefreshFrag();
        return root;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    FragmentManager fragmentManager;
    void RefreshFrag(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fragmentManager.beginTransaction().detach(this).commitNow();
            fragmentManager.beginTransaction().attach(this).commitNow();
        } else {
            fragmentManager.beginTransaction().detach(this).attach(this).commit();
        }
    }
}