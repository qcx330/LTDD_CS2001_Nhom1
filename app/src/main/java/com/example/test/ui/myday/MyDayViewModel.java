package com.example.test.ui.myday;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class MyDayViewModel extends ViewModel {

    private final MutableLiveData<String> txtGreat;

    public MutableLiveData<String> getTxtTime() {
        return txtTime;
    }

    private final MutableLiveData<String> txtTime;

    public MyDayViewModel() {

        txtGreat = new MutableLiveData<>();
        txtGreat.setValue("Good "+ getTime());

        txtTime = new MutableLiveData<>();
        txtTime.setValue(getDate());
    }
    public String getDate(){
        SimpleDateFormat simpleformat = new SimpleDateFormat("MMMM dd, yyyy");
        return simpleformat.format(new Date());
    }

    public String getTime(){
        Date date = new Date();
        int currentHour = date.getHours();
        if (currentHour >=0 && currentHour<= 12)
            return "Morning";
        else if (currentHour <= 17)
            return "Afternoon";
        else if (currentHour <= 20)
            return "Evening";
        else return "Night";
    }

    public LiveData<String> getTxtGrate() {
        return txtGreat;
    }
}