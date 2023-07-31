package com.example.test.controller;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.adapter.RcVwAdapter;
import com.example.test.model.TaskModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskController {
    List<TaskModel> lst = new ArrayList<>();
    RcVwAdapter adapter;
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
//    public void updateList(){
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds: snapshot.getChildren()){
//                    String id = ds.getKey();
//                    DatabaseReference idRef = FirebaseDatabase.getInstance().getReference("task").child(ds.getKey()).child("task");
//                    idRef.addChildEventListener(new ChildEventListener() {
//                        @Override
//                        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                            TaskModel task =  snapshot.child("id").getValue(TaskModel.class);
//                            lst.add(task);
//                            adapter.notifyDataSetChanged();
//                        }
//
//                        @Override
//                        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                        }
//
//                        @Override
//                        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//                        }
//
//                        @Override
//                        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
    public List<TaskModel> GetAllTask() {

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
    public TaskModel getTask(int id){
        TaskModel mtask = new TaskModel();
        databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("task")
                .orderByChild("id").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds: snapshot.getChildren()){
                            TaskModel task = ds.getValue(TaskModel.class);
                            mtask.setTask(task.getTask());
                            mtask.setId(task.getId());
                            mtask.setImpo(task.getImpo());
                            mtask.setDone(task.getDone());
                            Log.d("TEST", "value: "+ ds.child("task").getValue());
                            Log.d("TEST", "value: "+ ds.child("id").getValue());
                            Log.d("TEST", "task: "+ mtask.getTask());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return mtask;
    }
}