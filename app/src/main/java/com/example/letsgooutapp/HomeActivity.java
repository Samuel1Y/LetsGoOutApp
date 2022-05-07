package com.example.letsgooutapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.EventAdapter;
import com.example.letsgooutapp.ViewModel.EventViewModel;
import com.example.letsgooutapp.ViewModel.RegisterViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView eventList;
    private EventAdapter eventAdapter;
    private EventViewModel eventViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button ListFragmentButton = findViewById(R.id.ListFragmentButton);
        Button addEventFragmentButton = findViewById(R.id.AddEventFragmentButton);
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);

        ListFragmentButton.setOnClickListener(v-> navController.navigate(R.id.ListFragment));
        addEventFragmentButton.setOnClickListener(v-> navController.navigate(R.id.AddEventFragment));

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getAddedEvent().observe(this,new Observer<Event>() {

            @Override
            public void onChanged(Event event) {
                eventViewModel.getAddedEvent().getValue().getLatitude();
                eventViewModel.getAddedEvent().getValue().getLongitude();
                System.out.println(eventViewModel.getAddedEvent().toString());
            }
        });
    }

    public void showLocationOnMap(View view) {
    }

    public void openMap(View view)
    {
        startActivity(new Intent(this, MapActivity.class));
    }


    public void addEvent(View view) {

    }
}