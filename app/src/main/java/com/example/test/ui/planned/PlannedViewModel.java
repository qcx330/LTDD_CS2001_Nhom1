package com.example.test.ui.planned;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.controller.TaskController;
import com.example.test.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class PlannedViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private List<TaskModel> taskList = new ArrayList<>();
    TaskController taskController = new TaskController();

    public PlannedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is planned fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}