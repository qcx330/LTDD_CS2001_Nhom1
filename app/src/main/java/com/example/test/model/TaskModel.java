package com.example.test.model;

import java.util.Date;

public class TaskModel {
    private String task;
    private int done;
    private int impo;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    Date time;
    private static int id =1;
    {
        id++;
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
    public TaskModel(String task, Date time)
    {
        setTask(task);
        setImpo(0);
        setDone(0);
        setTime(time);
    }
    public TaskModel(){}
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
