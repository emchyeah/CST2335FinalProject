package com.example.cst2335finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

// example https://api.nasa.gov/planetary/apod?api_key=SUmqddAa2liUbdkcKxHvt2Umf2A6Z1a8rNuGkJsc&date=2022-11-23
// String baseURL = "https://api.nasa.gov/planetary/apod?api_key=SUmqddAa2liUbdkcKxHvt2Umf2A6Z1a8rNuGkJsc&date=";
// String parseURL = baseURL+ymd;
// date minimum is 1995-06-16

public class DatePicker extends BaseActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);
        Load();

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String stringMonth  = getMonthFormat(month);
                String date = makeDateString(year, stringMonth, day);
                String ymd = makeYMD(year, month, day);
                dateButton.setText(date);

                Log.d("oneDateSet()",ymd);

            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_WEEK);

        int style = AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(minDate());
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());


    }

    private long minDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(1995,05,16);
        return cal.getTimeInMillis();
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return makeDateString(year, getMonthFormat(month), day);
    }

    private String makeDateString(int year, String month, int day) {
        String formattedDate = month+" "+day+" "+year;
        return formattedDate;
    }

    private String makeYMD(int year, int month, int day) {
        String formattedDate = year+"-"+month+"-"+day;
        return formattedDate;
    }

    private String getMonthFormat(int month) {
        switch (month) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DEC";
        }
        return null;
    }


    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}