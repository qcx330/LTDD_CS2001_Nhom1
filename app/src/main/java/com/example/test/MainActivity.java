package com.example.test;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.example.test.databinding.ActivityMainBinding;
import com.example.test.model.TaskModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import com.example.test.controller.TaskController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    EditText edtTask;
    Button btnSave,btnSetDate, btnSetTime;
    BottomSheetDialog dialog;
    Calendar calendar = Calendar.getInstance();
    Calendar selected = Calendar.getInstance();
    SimpleDateFormat simpleformat = new SimpleDateFormat("MMMM dd, yyyy");
    int hours = calendar.get(Calendar.HOUR_OF_DAY);
    int minutes = calendar.get(Calendar.MINUTE);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

    SimpleDateFormat format = new SimpleDateFormat("h:mm aa");
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    TaskController taskController = new TaskController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Action bar
        setSupportActionBar(binding.appBarMain.toolbar);

        dialog = new BottomSheetDialog(this);
        createDialog();

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
        MenuItem item = navigationView.getMenu().findItem(R.id.nav_signOut);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, SignIn.class);
                startActivity(intent);
                finish();
                return true;
            }
        });
    }

    private void createDialog() {
        View view = getLayoutInflater().inflate(R.layout.add_new_task, null, false);

        dialog.setContentView(view);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
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
                    Toast.makeText(MainActivity.this, "Vui lòng nhập task", Toast.LENGTH_SHORT).show();
                else {
                    taskController.CreateTask(edtTask.getText().toString(), selected.getTime());
                    Toast.makeText(MainActivity.this, edtTask.getText().toString(), Toast.LENGTH_SHORT).show();
                    edtTask.setText("");
                }
            }
        });
        btnSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selected.set(Calendar.YEAR, year);
                        selected.set(Calendar.MONTH, month);
                        selected.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String time = simpleformat.format(selected.getTime());
                        btnSetDate.setText(time);
                    }
                }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        btnSetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selected.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selected.set(Calendar.MINUTE, minute);
                        selected.setTimeZone(TimeZone.getDefault());
                        String time = format.format(selected.getTime());
                        btnSetTime.setText(time);
                    }
                }, hours, minutes, false);
                timePickerDialog.show();

            }
        });
    }
    public String getCurrentDate(){

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