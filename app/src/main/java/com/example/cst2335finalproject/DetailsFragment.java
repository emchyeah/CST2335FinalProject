package com.example.cst2335finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {

        TextView date;
        TextView url;
        TextView title;

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_details_fragment,
                container, false);

        // initialize the textview by id
        date = view.findViewById(R.id.fragmentDate);
        url = view.findViewById(R.id.fragmentUrl);
        title = view.findViewById(R.id.fragmentTitle);

        // receives the information passed through the bundle
        Bundle b = getArguments();
        String imageDate = b.getString("DATE");
        String imageUrl = b.getString("URL");
        String imageTitle = b.getString("TITLE");

        // sets the information to the text areas
        date.setText(imageDate);
        url.setText(imageUrl);
        title.setText(imageTitle);

        return view;
    }
}