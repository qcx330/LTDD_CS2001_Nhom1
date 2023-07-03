package com.example.test.ui.myday;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyDayViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyDayViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my day fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}