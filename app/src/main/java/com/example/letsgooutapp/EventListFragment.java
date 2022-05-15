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
    private int mParam1;
    private String mParam2;

    private RecyclerView eventList;
    private EventAdapter eventAdapter;
    private EventViewModel eventViewModel;
    private ArrayList<Event> events = new ArrayList<>();
    private int selectedPosition;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventListFragment newInstance(int selectedPosition, String param2) {
        EventListFragment fragment = new EventListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, fragment.selectedPosition);
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
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventsFrom) {
                if (!eventsFrom.isEmpty()) {

                    eventAdapter = new EventAdapter((ArrayList<Event>) eventsFrom);
                    eventList.setAdapter(eventAdapter);

                    eventAdapter.setOnClickListener(event -> {
                        Toast.makeText(getContext(), event.getDescription(), Toast.LENGTH_SHORT).show();
                        selectedPosition = event.getId();
                        eventViewModel.setSelectedEventId(event.getId());
                    });
                }
            }
        });
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
    }
}