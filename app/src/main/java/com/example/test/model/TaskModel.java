package com.example.test.model;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TaskModel {
    private String task;
    private int done;
    private int impo;
    private Date date;
    private Date time;
    public List<StepModel> getStepList() {
        return stepList;
    }

    public void setStepList(List<StepModel> stepList) {
        this.stepList = stepList;
    }

    private List<StepModel> stepList;
    private static int id =1;
    {
        id++;
    }

    public TaskModel() {}

    public HashMap<String, Object> ConvertHashMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("impo", impo);
        hashMap.put("task", task);
        hashMap.put("done", done);
        return hashMap;
    }


    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }

    public int getImpo() {
        return impo;
    }

    public void setImpo(int impo) {
        this.impo = impo;
    }

    public int getId() {return id;}

    public void setId(int id){this.id = id;}
    public TaskModel(String task)
    {
        setTask(task);
        setImpo(0);
        setDone(0);
    }
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
}
