package com.example.test.ui.planned;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlannedViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PlannedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is planned fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}