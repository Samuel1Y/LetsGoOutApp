package com.example.letsgooutapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.EventAdapter;
import com.example.letsgooutapp.ViewModel.EventViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class EventListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView eventList;
    private EventAdapter eventAdapter;
    private EventViewModel eventViewModel;
    private ArrayList<Event> events = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventListFragment newInstance(String param1, String param2) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EventListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventsFrom) {
                if (!eventsFrom.isEmpty()) {
                    events.addAll(eventsFrom);
                    eventAdapter = new EventAdapter(events);
                    eventList.setAdapter(eventAdapter);

                    eventAdapter.setOnClickListener(event -> {
                        Toast.makeText(getContext(), event.getDescription(), Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
        //this.events = (ArrayList<Event>) eventViewModel.getAllEvents().getValue();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_event_list, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        eventList = view.findViewById(R.id.rv);
        eventList.hasFixedSize();
        eventList.setLayoutManager(new LinearLayoutManager(getContext()));

        //dummy data... later get ArrayList of all events from database
        /*ArrayList<Event> array = new ArrayList<Event>();
        array.add(new Event("going out", "just going out with few friends", "horsens", "me", 55.858141, 9.847580));
        array.add(new Event("house party", "party lmao", "Aarhus", "not me", 55.858231, 9.847688));
        array.add(new Event("going to restaurant", "me hungry me eat", "Vejle", "my friend", 55.858251, 9.848588));*/


    }
}