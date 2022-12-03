package com.example.cst2335finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SpaceListActivity extends AppCompatActivity {

    private ListView myList;
    private ArrayList<String> stuff;
    private MyListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_list);

        myList = findViewById(R.id.myList);

        //creates adapter
        myList.setAdapter(adapter = new MyListAdapter());
        //long click a row to show alert dialog
        myList.setOnItemLongClickListener((parent, view, pos, id) ->{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            //alert asks if you want to delete selected row
            alert.setTitle("Do you want to delete this?")
                    .setMessage("The selected row is: " + pos)

                    .setPositiveButton("Yes", (click, arg) -> {
                        stuff.remove(pos);
                        //updates adapter
                        adapter.notifyDataSetChanged();
                    })

                    .setNegativeButton("No", (click, arg) -> {

                    })

                    .create().show();
            return true;
        });
    }

    private class MyListAdapter extends BaseAdapter {

        public int getCount(){
            return stuff.size();
        }

        public Object getItem(int position){
            return stuff.get(position);
        }

        public long getItemId(int position){
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = getLayoutInflater();

            //make a new row:View newView = inflater.inflate(R.layout.row_layout, parent, false);

            //set what the text should be for this row:


            //return it to be put in the table
            return null;
        }
    }
}