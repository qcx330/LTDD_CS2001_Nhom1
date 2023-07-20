package com.example.test.model;

import android.widget.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class UserModel {
    private static int id = 0;
    private String userName;
    private String pass;
    private String confirmPass;
    private String email;

    private ImageView imgUser;
    private List<TaskModel> taskModelList;

    public HashMap<String, Object> ConvertHashMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("userName", userName);
        hashMap.put("pass", pass);
        hashMap.put("confirmPass", confirmPass);
        hashMap.put("email", email);
        hashMap.put("imgUser", imgUser);
        hashMap.put("taskModel", taskModelList);
        return hashMap;
    }

    public UserModel(String userName, String pass, String confirmPass, String email, ImageView imgUser , List<TaskModel> taskModelList) {
        this.userName = userName;
        this.pass = pass;
        this.confirmPass = confirmPass;
        this.email = email;
        this.imgUser = imgUser;
        this.taskModelList = taskModelList;
    }

    public UserModel(ImageView imgUser) {
        this.imgUser = imgUser;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserModel.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TaskModel> getTaskModelList() {
        return taskModelList;
    }

    public void setTaskModelList(List<TaskModel> taskModelList) {
        this.taskModelList = taskModelList;
    }

    public ImageView getImgUser() {
        return imgUser;
    }

    public void setImgUser(ImageView imgUser) {
        this.imgUser = imgUser;
    }
}
