package com.example.letsgooutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.letsgooutapp.Model.Event;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ListView eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        eventList = (ListView) findViewById(R.id.eventList);

        List<Event> array = new ArrayList<Event>();


        ArrayAdapter<Event> arrayAdapter = new ArrayAdapter<Event>(
                this,
                android.R.layout.simple_list_item_1,
                array );

        eventList.setAdapter(arrayAdapter);
    }


    public void addEvent(View view) {
    }
}