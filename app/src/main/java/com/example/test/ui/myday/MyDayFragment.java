package com.example.test.ui.myday;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.adapter.ItemTouchHelperListener;
import com.example.test.adapter.RcVwAdapter;
import com.example.test.adapter.RecyclerViewItemTouchHelper;
import com.example.test.databinding.FragmentMydayBinding;
import com.example.test.model.TaskModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MyDayFragment extends Fragment implements ItemTouchHelperListener{

    private FragmentMydayBinding binding;
    private RecyclerView recyView, recyViewTrue;

    List<TaskModel> lst, lstTrue;
    private RcVwAdapter adapter, adapterTrue;
    MyDayViewModel myDayViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
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

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyView);

        return root;
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof RcVwAdapter.TaskViewHolder){
            int idDelete = lst.get(viewHolder.getAdapterPosition()).getId();
            lst.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            adapter.removeItem(idDelete);
        }
    }
}
