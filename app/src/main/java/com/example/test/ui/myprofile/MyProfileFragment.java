package com.example.test.ui.myprofile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.test.databinding.FragmentMyprofileBinding;

public class MyProfileFragment extends Fragment {

    private FragmentMyprofileBinding binding;

    private MyProfileViewModel mViewModel;

    public static MyProfileFragment newInstance() {
        return new MyProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(MyProfileViewModel.class);
        binding = FragmentMyprofileBinding .inflate(inflater, container, false);
        View root = binding.getRoot();

        mViewModel.setImageAvatar(binding.imgAvatar);
        mViewModel.setEdtFullName(binding.edtFullName);
        mViewModel.setTxtEmail(binding.edtEmail);
        mViewModel.setBtnMyProfile(binding.btnUpdateProfile);




        return root;
    }

}