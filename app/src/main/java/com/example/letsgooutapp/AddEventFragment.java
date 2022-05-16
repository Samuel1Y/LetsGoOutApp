package com.example.letsgooutapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.Interest;
import com.example.letsgooutapp.Model.InterestAdapter;
import com.example.letsgooutapp.ViewModel.EventViewModel;
import com.example.letsgooutapp.ViewModel.InterestViewModel;
import com.example.letsgooutapp.ViewModel.RegisterViewModel;

import java.util.ArrayList;
import java.util.List;


public class AddEventFragment extends Fragment {

    private EditText titleText;
    private EditText descriptionText;
    private EditText locationText;

    private String title;
    private String description;
    private String location;

    private EventViewModel eventViewModel;
    private RegisterViewModel registerViewModel;
    private Account loggedInAcc = new Account();
    private ArrayList<Interest> interests = new ArrayList<>();
    private ArrayList<Boolean> interestsClicked = new ArrayList<>();
    private InterestAdapter interestAdapter;
    private InterestViewModel interestViewModel;
    private GridView interestsView;



    public AddEventFragment() {
        // Required empty public constructor
    }

    public static AddEventFragment newInstance(String param1, String param2, String param3) {
        AddEventFragment fragment = new AddEventFragment();
        Bundle args = new Bundle();
        args.putString("title", param1);
        args.putString("desctription", param2);
        args.putString("location", param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        interestsView = (GridView) view.findViewById(R.id.gridViewAddEvent);

        interestViewModel = new ViewModelProvider(this).get(InterestViewModel.class);
        interestViewModel.getAllInterests().observe(getViewLifecycleOwner(), new Observer<List<Interest>>() {
            @Override
            public void onChanged(List<Interest> interestsFrom) {
                if (!interestsFrom.isEmpty()) {
                    interests.addAll(interestsFrom);
                    interestAdapter = new InterestAdapter(getActivity(),interests, new ArrayList<>());
                    interestsView.setAdapter(interestAdapter);
                }
            }
        });
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getAddedEvent().observe(getViewLifecycleOwner(),new Observer<Event>() {

            @Override
            public void onChanged(Event event) {
                eventViewModel.getAddedEvent().getValue().getLatitude();
                eventViewModel.getAddedEvent().getValue().getLongitude();
                System.out.println(eventViewModel.getAddedEvent().toString());
            }
        });

        registerViewModel.getRegisteredUser().observe(getViewLifecycleOwner(), new Observer<Account>() {
            @Override
            public void onChanged(Account account) {
                loggedInAcc = account;
            }
        });



        titleText = view.findViewById(R.id.eventTitle);
        descriptionText = view.findViewById(R.id.eventDescription);
        locationText = view.findViewById(R.id.eventLocation);

        Button btn = view.findViewById(R.id.AddEventButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
                if(!title.equals("") && !description.equals("") && !location.equals(""))
                {
                    Event eventToAdd = new Event(title,description,location, loggedInAcc.getUsername(),eventViewModel.getAddedEvent().getValue().getLatitude(), eventViewModel.getAddedEvent().getValue().getLongitude());

                    ArrayList<Interest> chosenInterests = interestAdapter.getChosenInterests();
                    ArrayList<String> interestStrings = new ArrayList<>();
                    for(Interest interest:chosenInterests) {
                        interestStrings.add(interest.getInterest());
                    }
                    eventToAdd.setInterests(interestStrings);

                    eventViewModel.addEvent(eventToAdd);
                    System.out.println(title+", "+ description+", "+ location +", "+ eventViewModel.getAddedEvent().getValue().getLatitude()+", "+eventViewModel.getAddedEvent().getValue().getLongitude());

                    resetValues();
                }
                else
                {
                    Context context = view.getContext().getApplicationContext();
                    String text = "You must fill all of the attributes !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                }
            }
        });

        return view;
    }

    private void resetValues()
    {
        titleText.setText("");
        descriptionText.setText("");
        locationText.setText("");
    }

    private void loadData()
    {
        title = titleText.getText().toString();
        description = descriptionText.getText().toString();
        location = locationText.getText().toString();
    }
}