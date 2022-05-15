package com.example.letsgooutapp;

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

import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.Interest;
import com.example.letsgooutapp.Model.InterestAdapter;
import com.example.letsgooutapp.ViewModel.EventViewModel;
import com.example.letsgooutapp.ViewModel.InterestViewModel;
import com.example.letsgooutapp.ViewModel.RegisterViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEventFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEventFragment.
     */
    // TODO: Rename and change types and number of parameters
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
           // mParam1 = getArguments().getString(ARG_PARAM1);
           // mParam2 = getArguments().getString(ARG_PARAM2);
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