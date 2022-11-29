package com.example.cst2335finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    TextView nameText;
    Button changeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        nameText = findViewById(R.id.name);

        String newTextView = nameText.getText().toString();

        Intent dataSent = getIntent();
        //getting data from previous page
        String dataName = dataSent.getStringExtra("name");

        nameText.setText(newTextView + " " + dataName + "!");

        changeName = findViewById(R.id.changeName);
        changeName.setOnClickListener(click -> {
            setResult(0, dataSent);
            //goes back to previous activity
            finish();
        });


    }
}