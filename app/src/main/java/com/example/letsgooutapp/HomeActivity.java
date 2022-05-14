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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView eventList;
    private EventAdapter eventAdapter;
    private EventViewModel eventViewModel;
    private RegisterViewModel registerViewModel;
    private int selectedId;
    private Account loggedInAcc = new Account();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button listFragmentButton = findViewById(R.id.ListFragmentButton);
        Button addEventFragmentButton = findViewById(R.id.AddEventFragmentButton);
        Button profileFragmentButton = findViewById(R.id.ProfileFragmentButton);

        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);

        listFragmentButton.setOnClickListener(v-> navController.navigate(R.id.ListFragment));
        addEventFragmentButton.setOnClickListener(v-> navController.navigate(R.id.AddEventFragment));
        profileFragmentButton.setOnClickListener(v-> navController.navigate(R.id.ProfileFragment));

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getAddedEvent().observe(this,new Observer<Event>() {

            @Override
            public void onChanged(Event event) {
                eventViewModel.getAddedEvent().getValue().getLatitude();
                eventViewModel.getAddedEvent().getValue().getLongitude();
                System.out.println(eventViewModel.getAddedEvent().toString());
            }
        });
        eventViewModel.getSelectedEventId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                selectedId = eventViewModel.getSelectedEventId().getValue();
                System.out.println("HOME ACVITIVY DASJDNKJNDKASJDSJKADSDN    " +selectedId);
            }
        });

        eventViewModel.getParticipantsByEventId(selectedId).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println(s);
            }
        });

        registerViewModel.getRegisteredUser().observe(this, new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                loggedInAcc = account;
            }
        });
    }

    public void deleteEvent(View view) {
        eventViewModel.deleteEventById(selectedId);
    }

    public void openMap(View view)
    {
        startActivity(new Intent(this, MapActivity.class));
    }


    public void addEvent(View view) {

    }
    public void joinEvent(View view) {
        // NOT WORKING !!!!!!!!!!!!!!!!!!!!!!!!!

        //floating button
        ArrayList<String> participants = new ArrayList<>();
        participants.add(loggedInAcc.getUsername());
        System.out.println(loggedInAcc.getUsername());
        eventViewModel.updateParticipantsOfEvent(participants, selectedId);

        String participantsString = eventViewModel.getParticipantsByEventId(selectedId).getValue();
        //participants.add(registerViewModel.getRegisteredUser().getValue().getUsername());
        //eventViewModel.updateParticipantsOfEvent(participants, selectedId);
    }
}