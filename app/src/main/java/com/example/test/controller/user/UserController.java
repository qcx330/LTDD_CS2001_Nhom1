package com.example.test.controller.user;

import com.example.test.controller.task.TaskController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserController {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Users");
//    public void addTask(String nameTask){
//        TaskController taskController = new TaskController();
//        taskController.CreateTask(nameTask);
//        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(taskController);
//    }
}
