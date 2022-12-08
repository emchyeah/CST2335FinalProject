package com.example.cst2335finalproject;

import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    Button button;
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Load();

        button = findViewById(R.id.button);
        nameInput = findViewById(R.id.nameInput);

        //creates the intent to go to the next Activity (DatePicker)
        Intent nextPage = new Intent(this, DatePicker.class);
        //button is clicked, saves the EditText and brings it to the next Activity
        button.setOnClickListener(v -> {
            //if there is an input in the EditText
            if (nameInput.getText().length() > 0) {
                nextPage.putExtra("name", nameInput.getText().toString());
                startActivityForResult(nextPage, 1);
                nameInput.getText().clear();

            } //if there is no input in the EditText, creates a short popup message for user
            else {
                Toast.makeText(this, "Please enter a name :)", Toast.LENGTH_SHORT).show();
            }

        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        //stores the name from the EditText into shared preferences for next time the person
        //opens the app, the name will be saved
        SharedPreferences prefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("name", nameInput.getText().toString());
        edit.commit();
    }
}
