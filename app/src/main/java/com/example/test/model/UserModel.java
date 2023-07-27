package com.example.test.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserModel {
    private String email;
    private List<TaskModel> lst;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public UserModel(String email) {
        setEmail(email);
        lst = new ArrayList<>();
    }

    public UserModel(){}
}
