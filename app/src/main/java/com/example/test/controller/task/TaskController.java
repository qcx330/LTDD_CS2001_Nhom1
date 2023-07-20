package com.example.test.controller.task;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.model.TaskModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class TaskController extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void CreateTask(String nameTask) {


        // private String task;
        //    private int done;
        //    private int impo;
        //    private static int id = 0;

//        CheckBox chbxDone = findViewById(R.id.chbxDone);
//        CheckBox chbxImp = findViewById(R.id.chbxImp);

        TaskModel taskModel = new TaskModel(nameTask);

        db.collection("TaskModel")
                .document(String.valueOf(taskModel.getId()))
                .set(taskModel.ConvertHashMap())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Ghi chú: ", "Thêm thành công");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Ghi chú: ", "Thêm thất bại");
                    }
                });

    }

    public ArrayList<TaskModel> GetAllTask() {
        ArrayList<TaskModel> lst = new ArrayList<>();
        db.collection("TaskModel")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                            for (QueryDocumentSnapshot document: task.getResult()) {
                                TaskModel tsk = document.toObject(TaskModel.class);
                                lst.add(tsk);
                            }
                    }
                });
        return lst;
    }
}
