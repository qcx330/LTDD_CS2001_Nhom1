package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.controller.TaskController;
import com.example.test.model.TaskModel;

public class TaskDetail extends AppCompatActivity {
    TaskController taskController;
    Button btnSave;
    TextView txtTask;
    CheckBox chboxDone;
    TaskModel task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        Intent intent = getIntent();
        String task = intent.getStringExtra("task");
        int id = intent.getIntExtra(("id"),0);
        int done = intent.getIntExtra(("done"),0);
        int impo = intent.getIntExtra(("impo"),0);

        chboxDone = findViewById(R.id.chbxDone);
        btnSave = findViewById(R.id.btnSaveEdit);
        txtTask = findViewById(R.id.txtTask);
        txtTask.setText(task);
        if (done==0){
            chboxDone.setChecked(false);
        }
        else chboxDone.setChecked(true);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("TEST-ID", "task"+task);
            }
        });
    }
}