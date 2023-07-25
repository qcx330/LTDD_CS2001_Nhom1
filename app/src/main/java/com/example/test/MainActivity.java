package com.example.test;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.test.adapter.RcVwAdapter;
//import com.example.test.controller.task.TaskController;
import com.example.test.controller.task.TaskController;
import com.example.test.model.TaskModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.units.qual.C;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {
    EditText edtTask;
    Button btnSave, btnSetDate, btnSetTime;
    TextView txtTime, txtDate;
    BottomSheetDialog dialog;
    RecyclerView recyclerView;
    Calendar calendar = Calendar.getInstance();
    int hours = calendar.get(Calendar.HOUR_OF_DAY);
    int minutes = calendar.get(Calendar.MINUTE);
    SimpleDateFormat format = new SimpleDateFormat("h:mm aa");
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    TaskController taskController = new TaskController();
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Action bar
        setSupportActionBar(binding.appBarMain.toolbar);

        dialog = new BottomSheetDialog(this);
        createDialog();

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("/mm");

        myRef.setValue("Hello, World!");

        //Floating button
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_myDay, R.id.nav_planned, R.id.nav_important,
                R.id.nav_assigned, R.id.nav_tasks
        )
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //
//        recyclerView = findViewById(R.id.recycleView);
    }

    private void createDialog() {
        View view = getLayoutInflater().inflate(R.layout.add_new_task, null, false);

        dialog.setContentView(view);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        //dialog.getWindow().getAttributes().windowAnimations =R.style.DialogAnimation;
        btnSave = dialog.findViewById(R.id.btnSave);
        edtTask = dialog.findViewById(R.id.edtTask);
        btnSetDate = dialog.findViewById(R.id.btnSetDate);
        btnSetDate.setText(getCurrentDate());
        btnSetTime = dialog.findViewById(R.id.btnSetTime);
        btnSetTime.setText(getCurrentTime());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (edtTask.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, "Nhap task", Toast.LENGTH_SHORT).show();
                else {
                    taskController.CreateTask(edtTask.getText().toString());
                    Toast.makeText(MainActivity.this, edtTask.getText().toString(), Toast.LENGTH_SHORT).show();
                    edtTask.setText("");
                }
            }
        });
        boolean isOkayClicked;
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker materialDatePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date").build();
                materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        btnSetDate.setText(materialDatePicker.getHeaderText());
                    }
                });
                materialDatePicker.show(getSupportFragmentManager(), "TAG");
            }
        });
        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.setTimeZone(TimeZone.getDefault());
                        String time = format.format(c.getTime());
                        btnSetTime.setText(time);
                    }
                }, hours, minutes, false);
                timePickerDialog.show();

            }
        });
    }

    public String getCurrentDate(){
        SimpleDateFormat simpleformat = new SimpleDateFormat("MMMM dd, yyyy");
        return simpleformat.format(new Date());
    }

    public String getCurrentTime(){
        return format.format(calendar.getTime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}