package com.example.test.model;

import java.util.Date;

public class TaskModel {
    private String task;
//    private int id;

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

    private int done;

    //private Date remindDate;
    //public TaskModel(){}
    public TaskModel(String task)
    {
        setTask(task);
        setDone(0);
        setImpo(0);
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
    private int impo;
}
