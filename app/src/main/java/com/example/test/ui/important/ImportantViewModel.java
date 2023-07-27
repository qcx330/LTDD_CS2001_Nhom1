package com.example.test.ui.important;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.controller.TaskController;
import com.example.test.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class ImportantViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    private MutableLiveData<List<TaskModel>> taskListLiveData;
    private List<TaskModel> taskList = new ArrayList<>();
    TaskController taskController = new TaskController();

    public ImportantViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is important fragment");

        taskListLiveData = new MutableLiveData<>();
        taskListLiveData.setValue(getList());
    }

    public List<TaskModel> getList(){
        taskList = taskController.GetAllTask();
        return taskList;
    }

    public LiveData<String> getText() {
        return mText;
    }
}