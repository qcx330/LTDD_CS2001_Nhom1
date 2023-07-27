package com.example.test.controller;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.model.TaskModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskController {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Users");

    public void CreateTask(String nameTask, Date time) {
        TaskModel taskModel = new TaskModel(nameTask, time);
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("task")
                .child(nameTask)
                .setValue(taskModel);
    }

    public void EditTask(String nameTask, String nameCheck, int checkDone) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef()
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("task")
                        .child(nameTask)
                        .child(nameCheck)
                        .setValue(checkDone);

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("task");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            if (dataSnapshot.getValue(TaskModel.class).getDone() == 1)
                                Log.d("test", String.valueOf(dataSnapshot.getValue(TaskModel.class).getDone()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public List<TaskModel> GetAllTask() {
        List<TaskModel> lst = new ArrayList<>();
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("task")
                .orderByKey().addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        lst.add(snapshot.getValue(TaskModel.class));
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
        return lst;
    }
}