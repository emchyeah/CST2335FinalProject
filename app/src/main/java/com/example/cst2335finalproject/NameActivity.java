package com.example.cst2335finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NameActivity extends AppCompatActivity {

    TextView nameText;
    Button changeName;
    Button pix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        nameText = findViewById(R.id.name);
        String newTextView = nameText.getText().toString();

        Intent dataSent = getIntent();
        //getting data from previous page
        String dataName = dataSent.getStringExtra("name");

        //sets the TextView to show the name set from MainActivity to this activity
        nameText.setText(newTextView + " " + dataName + "!");

        changeName = findViewById(R.id.changeName);
        //button click goes to previous activity
        changeName.setOnClickListener(click -> {
            setResult(0, dataSent);
            finish();
        });

        //creates the intent to go to next activity
        Intent intent = new Intent(this, SpaceListActivity.class);
        pix = findViewById(R.id.Space);
        //button click goes to the next activity
        pix.setOnClickListener(click -> startActivity(intent));



    }
}