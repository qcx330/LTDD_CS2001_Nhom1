package com.example.test.ui.assigned;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AssignedViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public AssignedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is assigned to me fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}