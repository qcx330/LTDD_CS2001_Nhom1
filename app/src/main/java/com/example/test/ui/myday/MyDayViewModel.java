package com.example.test.ui.myday;

import android.app.Application;
import android.text.Layout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.adapter.RcVwAdapter;
import com.example.test.controller.task.TaskController;
import com.example.test.model.TaskModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyDayViewModel extends ViewModel {
    private final MutableLiveData<String> txtGreat;

    public MutableLiveData<List<TaskModel>> getTaskListLiveData() {
        return taskListLiveData;
    }

    private MutableLiveData<List<TaskModel>> taskListLiveData;
    private List<TaskModel> taskList;
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
    private List<TaskModel> getList(){
        taskList = new ArrayList<>();
        taskList = taskController.GetAllTask();
        return taskList;
    }
    public String getDate(){
        SimpleDateFormat simpleformat = new SimpleDateFormat("MMMM dd, yyyy");
        return simpleformat.format(new Date());
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
//    public List<TaskModel> getTaskModelList()
//    {
//        return this.taskModelList;
//    }
//
//
////    public List<TaskModel> addTaskModelListTrue(){
////        for(int i = 0; i < taskModelList.size(); i++)
////        {
////            if(taskModelList.get(i).getDone() == 1)
////            {
////                taskModelListTrue.add(taskModelList.get(i));
////                taskModelList.remove(taskModelList.get(i));
////            }
////        }
////        return taskModelListTrue;
////    }

//    public List<TaskModel> addTaskModelList(){
////        taskModelList.add(new TaskModel("Home Work A"));
////        taskModelList.add(new TaskModel("Home Work B"));
////        taskModelList.add(new TaskModel("Home Work C"));
////        taskModelList.add(new TaskModel("Home Work D"));
////        taskModelList.add(new TaskModel("Home Work E"));
//        taskModelList = taskController.GetAllTask();
//        ArrayList<TaskModel> list = new ArrayList<>();
//        for(TaskModel i :taskModelList){
//            list.add(i);
//        }
//        return list;
//    }

    public LiveData<String> getTxtGrate() {
        return txtGreat;
    }
}