package com.example.cst2335finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

//    load elements
    void Load() {
//        toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        drawer layout
        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        navigation view
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // for selecting items on the toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        String message = null;

        String item1 = getString(R.string.item1);
        String item2 = getString(R.string.item2);

        switch(item.getItemId()) {
            case R.id.item1:
                message = "You clicked on " + item1;
                break;
            case R.id.item2:
                message = "You clicked on " + item2;
                break;
        }

        // message appears at the bottom
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return true;
    }

//    on select for navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            // to home
            case R.id.actionHome:
                Intent intentHome = new Intent(this, MainActivity.class);
                startActivity(intentHome);
                break;
            // to date picker
            case R.id.actionDatePicker:
                Intent intentDatePicker = new Intent(this, DatePicker.class);
                startActivity(intentDatePicker);
                break;
            // exit app
            case R.id.actionExit:
                finishAffinity();
                break;
        }
        return false;
    }

//    displays toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

}
