package com.example.cst2335finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DatePicker extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);
        Load();
    }
}