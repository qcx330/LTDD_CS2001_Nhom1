package com.example.test.ui.myday;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.adapter.RcVwAdapter;
import com.example.test.databinding.FragmentMydayBinding;
import com.example.test.model.TaskModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyDayFragment extends Fragment {

    private FragmentMydayBinding binding;
    private RecyclerView recyView, recyViewTrue;

    List<TaskModel> lst, lstTrue;
    private RcVwAdapter adapter, adapterTrue;
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

        lst = new ArrayList<>();

        recyView = binding.recycleView;
        recyView.setHasFixedSize(true);
        recyView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RcVwAdapter(getContext(), lst);
        recyView.setAdapter(adapter);
        myDayViewModel.getList(lst, adapter, "MyDay");

//        lstTrue = new ArrayList<>();
//        recyViewTrue = binding.recycleViewTrue;
//        recyViewTrue.setHasFixedSize(true);
//        recyViewTrue.setLayoutManager(new LinearLayoutManager(getContext()));
//        adapterTrue = new RcVwAdapter(getContext(), lstTrue);
//        recyViewTrue.setAdapter(adapterTrue);
//        myDayViewModel.getListTrue(lstTrue, adapterTrue);


        myDayViewModel.DeleteTask(lst, adapter, recyView);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}