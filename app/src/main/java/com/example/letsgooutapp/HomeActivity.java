package com.example.letsgooutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.EventAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView eventList;
    private EventAdapter eventAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        eventList = findViewById(R.id.rv);
        eventList.hasFixedSize();
        eventList.setLayoutManager(new LinearLayoutManager(this));

        //dummy data... later get ArrayList of all events from database
        ArrayList<Event> array = new ArrayList<Event>();
        array.add(new Event("going out","just going out with few friends","horsens","me"));
        array.add(new Event("house party","party lmao","Aarhus","not me"));
        array.add(new Event("going to restaurant","me hungry me eat","Vejle","my friend"));

        eventAdapter = new EventAdapter(array);
        eventList.setAdapter(eventAdapter);
    }


    public void addEvent(View view) {
    }

    public void showLocationOnMap(View view) {
    }
}