package com.example.test.ui.important;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.adapter.RcVwAdapter;
import com.example.test.controller.TaskController;
import com.example.test.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class ImportantViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    TaskController taskController = new TaskController();

    public ImportantViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is important fragment");
    }

    public void getList(List<TaskModel> lst, RcVwAdapter adapter, String nameFragment) {
//        taskController.GetTask(lst, adapter, nameFragment);
    }

    public LiveData<String> getText() {
        return mText;
    }
}