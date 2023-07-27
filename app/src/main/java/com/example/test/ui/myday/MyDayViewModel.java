package com.example.test.ui.myday;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test.controller.TaskController;
import com.example.test.model.TaskModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDayViewModel extends ViewModel {
    private final MutableLiveData<String> txtGreat;

    public MutableLiveData<List<TaskModel>> getTaskListLiveData() {
        return taskListLiveData;
    }

    private MutableLiveData<List<TaskModel>> taskListLiveData;
    private List<TaskModel> taskList = new ArrayList<>();
    TaskController taskController = new TaskController();

    public MutableLiveData<String> getTxtTime() {
        return txtTime;
    }
    private final MutableLiveData<String> txtTime;

    public MyDayViewModel() {

        txtGreat = new MutableLiveData<>();
        txtGreat.setValue("Good "+ getTime());
        txtTime = new MutableLiveData<>();
        txtTime.setValue(getDate());


        taskListLiveData = new MutableLiveData<>();
        taskListLiveData.setValue(getList());
    }
    public List<TaskModel> getList(){
        taskList = taskController.GetAllTask();
        return taskList;
    }
    public String getDate(){
        SimpleDateFormat simpleFormat = new SimpleDateFormat("MMMM dd, yyyy");
        return simpleFormat.format(new Date());
    }

    public String getTime(){
        Date date = new Date();
        int currentHour = date.getHours();
        if (currentHour >=0 && currentHour<= 12)
            return "Morning";
        else if (currentHour <= 17)
            return "Afternoon";
        else if (currentHour <= 20)
            return "Evening";
        else return "Night";
    }

    public LiveData<String> getTxtGrate() {
        return txtGreat;
    }
}