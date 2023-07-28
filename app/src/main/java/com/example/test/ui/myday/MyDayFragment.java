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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.adapter.RcVwAdapter;
import com.example.test.databinding.FragmentMydayBinding;
import com.example.test.model.TaskModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyDayFragment extends Fragment {

    private FragmentMydayBinding binding;
    private RecyclerView recyView;
    private RcVwAdapter adapter;
    MyDayViewModel myDayViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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
                try {
                    adapter = new RcVwAdapter(getContext(),taskModels);
                    Thread.sleep(1000);
                    recyView.setAdapter(adapter);
                    recyView.setLayoutManager(new LinearLayoutManager(getContext()));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyView);

        return root;
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int postion = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                    myDayViewModel.getTaskListLiveData().removeObserver(new Observer<List<TaskModel>>() {
                        @Override
                        public void onChanged(List<TaskModel> taskModelList) {
                            taskModelList.remove(postion);
                            adapter.notifyItemRemoved(postion);
                        }
                    });
                    break;
                case ItemTouchHelper.RIGHT:
                    break;
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}