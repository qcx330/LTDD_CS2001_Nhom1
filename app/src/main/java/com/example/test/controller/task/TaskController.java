package com.example.test.controller.task;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.model.TaskModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class TaskController extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("Test");
    public void CreateTask(String nameTask) {

        TaskModel taskModel = new TaskModel(nameTask);
        databaseReference.push().setValue(taskModel);


//        db.collection("TaskModel")
//                .document(String.valueOf(taskModel.getId()))
//                .set(taskModel.ConvertHashMap())
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("Ghi chú: ", "Thêm thành công");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("Ghi chú: ", "Thêm thất bại");
//                    }
//                });

    }

    public List<TaskModel> GetAllTask() {
//        db.collection("TaskModel")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful())
//                            for (QueryDocumentSnapshot document: task.getResult()) {
//                                TaskModel tsk = document.toObject(TaskModel.class);
//                                lst.add(tsk);
//                            }
//                    }
//                });

        List<TaskModel> lst = new ArrayList<>();
        databaseReference.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                lst.add(snapshot.getValue(TaskModel.class));
                Log.d("SNAP", snapshot.getValue(TaskModel.class).toString());
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