package com.example.test.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class TaskModel {
    private String task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public HashMap<String, Object> convertHashMap(){
        HashMap<String,Object> hashmap = new HashMap<>();
        hashmap.put("id",id);
        hashmap.put("task", task);
        hashmap.put("impo",impo);
        hashmap.put("done", done);
        return hashmap;
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
