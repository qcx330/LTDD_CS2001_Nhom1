package com.example.test.ui.assigned;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.test.databinding.FragmentAssignedBinding;

public class AssignedFragment extends Fragment {

    private FragmentAssignedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AssignedViewModel homeViewModel =
                new ViewModelProvider(this).get(AssignedViewModel.class);

        binding = FragmentAssignedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.txtAss;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}