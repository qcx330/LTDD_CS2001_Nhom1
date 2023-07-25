package com.example.test.controller.task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.model.TaskModel;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TaskController extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference databaseReference = database.getReference("Test");
    public void CreateTask(String nameTask) {
        TaskModel taskModel = new TaskModel(nameTask);
        databaseReference.child(nameTask).setValue(taskModel);
    }

    public void EditTask(String nameTask, int checkDone){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child(nameTask).child("done").setValue(checkDone);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }

    public List<TaskModel> GetAllTask() {
        List<TaskModel> lst = new ArrayList<>();
        databaseReference.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                lst.add(snapshot.getValue(TaskModel.class));
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        return lst;
    }
}