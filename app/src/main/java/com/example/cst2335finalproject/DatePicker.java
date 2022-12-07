package com.example.cst2335finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

// example https://api.nasa.gov/planetary/apod?api_key=SUmqddAa2liUbdkcKxHvt2Umf2A6Z1a8rNuGkJsc&date=2022-11-23
// String baseURL = "https://api.nasa.gov/planetary/apod?api_key=SUmqddAa2liUbdkcKxHvt2Umf2A6Z1a8rNuGkJsc&date=";
// String parseURL = baseURL+ymd;
// date minimum is 1995-06-16

public class DatePicker extends BaseActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private List<NASAImage> imageList = new ArrayList<>();
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);
        Load();

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        ListView listView = findViewById(R.id.listView);
        adapter = new Adapter();
        listView.setAdapter(adapter);

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

                Log.d("onDateSet()",ymd);
                final String baseUrl = "https://api.nasa.gov/planetary/apod?api_key=";
                final String apiKey = "SUmqddAa2liUbdkcKxHvt2Umf2A6Z1a8rNuGkJsc";
                String parseUrl = baseUrl + apiKey + "&date=" + ymd;
                Log.d("onDateSet()",parseUrl);

                NASA nasa = new NASA();
//                using cat random cat image because NASA JSON is slow
                nasa.execute("https://cataas.com/cat?json=true");

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

//    parses through the NASA image of the date selected JSON
    private class NASA extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {

                // get response from host
                InputStream response = request(strings[0]);

                // parse through JSON objects
                String result = parser(response);
                JSONObject obj = new JSONObject(result);

//                log JSON result
                Log.d("NASA 3 ", result);

//                get JSON details
//                using random cat image, remember to change these back to NASA
                String date = obj.getString("createdAt");
                String url = obj.getString("url");
                String title = obj.getString("_id");

//                create NASAImage object
                NASAImage nasaImage = new NASAImage(date, url, title);
                imageList.add(nasaImage);

//                log JSON details
                Log.d("NASA", date);
                Log.d("NASA", url);
                Log.d("NASA", title);

            }
            catch (IOException e) {
                Log.d("NASA","Issue with request/response");
                e.printStackTrace();
            }
            catch (JSONException e) {
                Log.d("NASA", "Issue parsing JSON");
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            adapter.notifyDataSetChanged();

        }
    }

//    send request to host
    public InputStream request (String x) throws IOException {
        URL url = new URL(x);
        Log.d("input stream", x);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Log.d("input stream", "AAAAAAAA");
        return connection.getInputStream();
    }

//    parses through JSON
    public String parser(InputStream response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(response));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = br.readLine()) !=null) {
            sb.append(line+ "\n");
        }
        return sb.toString();
    }

    private class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public NASAImage getItem(int position) {
            return imageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View v = getLayoutInflater().inflate(R.layout.activity_nasaimage, viewGroup, false);
            TextView title = v.findViewById(R.id.imageTitle);
            NASAImage nasaImage = getItem(position);
            title.setText(nasaImage.getTitle());
            return v;
        }
    }

}