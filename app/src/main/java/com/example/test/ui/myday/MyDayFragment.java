package com.example.test.ui.myday;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.adapter.RcVwAdapter;
import com.example.test.controller.task.TaskController;
import com.example.test.databinding.FragmentMydayBinding;
import com.example.test.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class MyDayFragment extends Fragment {

    private FragmentMydayBinding binding;
    private RecyclerView recyView;
    private RcVwAdapter adapter;
    List<TaskModel> lst;
    MyDayViewModel myDayViewModel;
    TaskController taskController = new TaskController();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        RefreshFrag();
        myDayViewModel =
                new ViewModelProvider(this).get(MyDayViewModel.class);
        binding = FragmentMydayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGreat;
        myDayViewModel.getTxtGrate().observe(getViewLifecycleOwner(),textView::setText);

        final TextView textView1 = binding.txtTime;
        myDayViewModel.getTxtTime().observe(getViewLifecycleOwner(),textView1::setText);

        recyView = binding.recycleView;
        myDayViewModel.getTaskListLiveData().observe(getViewLifecycleOwner(), new Observer<List<TaskModel>>() {
            @Override
            public void onChanged(List<TaskModel> taskModels) {
                adapter = new RcVwAdapter(taskModels);
                recyView.setAdapter(adapter);
                recyView.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
//        lst = taskController.GetAllTask();
//        adapter = new RcVwAdapter(lst);
//        recyView.setAdapter(adapter);
//        recyView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }
//    Observer<List<TaskModel>> changeObserver = new Observer<List<TaskModel>>() {
//    @Override
//    public void onChanged(List<TaskModel> taskModels) {
//        adapter = new RcVwAdapter(taskModels);
//        recyView.setAdapter(adapter);
//        recyView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//};
//
//}};
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        myDayViewModel.getTaskListLiveData().observe(this, changeObserver);
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
//    void RefreshFrag(){
//        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
//    }
}