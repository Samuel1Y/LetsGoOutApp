package com.example.letsgooutapp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.EventAdapter;
import com.example.letsgooutapp.Model.Interest;
import com.example.letsgooutapp.Model.InterestAdapter;
import com.example.letsgooutapp.ViewModel.EventViewModel;
import com.example.letsgooutapp.ViewModel.InterestViewModel;
import com.example.letsgooutapp.ViewModel.RegisterViewModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView profileUsername;
    private ImageView profilePicture;
    private GridView interestsView;
    private ArrayList<Integer> myInterests = new ArrayList<>();
    private ArrayList<Interest> interests = new ArrayList<>();
    private ArrayList<Boolean> interestsClicked = new ArrayList<>();
    private InterestAdapter interestAdapter;
    private InterestViewModel interestViewModel;
    private RegisterViewModel registerViewModel;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerViewModel.getAllInterests().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> stringsInterests) {
                if (!stringsInterests.isEmpty()) {
                    if (!interests.isEmpty()) {
                        myInterests = new ArrayList<>();
                        Gson gson = new Gson();
                        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
                        ArrayList<String> stringArray = gson.fromJson(stringsInterests.get(0), listType);
                        for (String stringId : stringArray) {
                            //Interest interestToAdd = interests.get(Integer.parseInt(stringId));
                            myInterests.add(Integer.parseInt(stringId));
                            interestAdapter = new InterestAdapter(getActivity(),interests, myInterests);
                            interestsView.setAdapter(interestAdapter);
                            //interestAdapter.setArrayClickedToTrue(Integer.parseInt(stringId));
                        }
                    }
                }
            }
        });
        interestViewModel = new ViewModelProvider(this).get(InterestViewModel.class);
        interestViewModel.getAllInterests().observe(this, new Observer<List<Interest>>() {
            @Override
            public void onChanged(List<Interest> interestsFrom) {
                if (!interestsFrom.isEmpty()) {
                    interests.addAll(interestsFrom);
                    interestAdapter = new InterestAdapter(getActivity(),interests, myInterests);
                    interestsView.setAdapter(interestAdapter);
                    /*interestsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(getActivity(),"You Clicked on "+ interests.get(position).getInterest(),Toast.LENGTH_SHORT).show();
                            interestsClicked.set(position, !interestsClicked.get(position));
                        }
                    });*/
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        interestsView = (GridView) view.findViewById(R.id.gridView);

        profileUsername = view.findViewById(R.id.profileUsername);
        profilePicture = view.findViewById(R.id.profilePicture);

        Button btn = view.findViewById(R.id.editInterests);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Interest> chosenInterests = interestAdapter.getChosenInterests();
                registerViewModel.setInterests(chosenInterests);
            }
        });

        /*interestAdapter = new InterestAdapter(getActivity(),interests);
        interestsView.setAdapter(interestAdapter);
        interestsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getActivity(),"You Clicked on "+ interests.get(position).getInterest(),Toast.LENGTH_SHORT).show();
            }
        });*/
        return view;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}