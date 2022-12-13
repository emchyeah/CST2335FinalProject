package com.example.cst2335finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlanetActivity extends BaseActivity {

    TextView planets;
    EditText planetOne, planetTwo, planetThree, planetFour, planetFive, planetSix, planetSeven, planetEight;
    Button answerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
        //loads Base activity
        Load();
        //sets title of page
        setTitle(R.string.planetPageTitle);

        planets = findViewById(R.id.planets);
        planetOne = findViewById(R.id.planetOne);
        planetTwo = findViewById(R.id.planetTwo);
        planetThree = findViewById(R.id.planetThree);
        planetFour = findViewById(R.id.planetFour);
        planetFive = findViewById(R.id.planetFive);
        planetSix = findViewById(R.id.planetSix);
        planetSeven = findViewById(R.id.planetSeven);
        planetEight = findViewById(R.id.planetEight);
        answerButton = findViewById(R.id.answerButton);

        answerButton.setOnClickListener(click-> {
            planetPopUp();
        });


    }

    private void planetPopUp() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final View helpPopUp = getLayoutInflater().inflate(R.layout.planetanswer, null);
        Button closeAnswer = helpPopUp.findViewById(R.id.planetClose);

        dialogBuilder.setView(helpPopUp);
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        //closes popup window on click
        closeAnswer.setOnClickListener(click -> {
            dialog.dismiss();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //stores the name from the EditText into shared preferences for next time the person
        //opens the app, the name will be saved
        SharedPreferences prefs = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        //takes the EditText and puts it into shared prefs under "name" key
        edit.putString("planetOne", planetOne.getText().toString());
        edit.putString("planetTwo", planetTwo.getText().toString());
        edit.putString("planetThree", planetThree.getText().toString());
        edit.putString("planetFour", planetFour.getText().toString());
        edit.putString("planetFive", planetFive.getText().toString());
        edit.putString("planetSix", planetSix.getText().toString());
        edit.putString("planetSeven", planetSeven.getText().toString());
        edit.putString("planetEight", planetEight.getText().toString());
        //commits the sharedprefs
        edit.commit();
    }
}