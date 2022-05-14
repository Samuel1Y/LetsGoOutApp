package com.example.letsgooutapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.EventAdapter;
import com.example.letsgooutapp.ViewModel.EventViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.letsgooutapp.databinding.ActivityMapBinding;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapBinding binding;
    private Marker eventMarker;
    final LatLng horsensLocation = new LatLng(55.858131, 9.847588);
    private Bundle bundle;
    private EventViewModel eventViewModel;
    private ArrayList<Event> events;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = savedInstanceState;

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        eventViewModel.getAddedEvent().observe(this, new Observer<Event>() {

            @Override
            public void onChanged(Event event) {
                eventViewModel.getAddedEvent().getValue().getLatitude();
                eventViewModel.getAddedEvent().getValue().getLongitude();
                System.out.println(eventViewModel.getAddedEvent().toString());
            }
        });
        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> eventsFrom) {
                if (!eventsFrom.isEmpty()) {
                    //events.addAll(eventsFrom);
                    for (int i = 0; i < eventsFrom.size(); i++)
                    {
                        LatLng position = new LatLng(eventsFrom.get(i).getLatitude(),eventsFrom.get(i).getLongitude());
                        mMap.addMarker(
                                new MarkerOptions()
                                        .position(position)
                                        .title(eventsFrom.get(i).getTitle()));
                    }
                }
            }
        });

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Horsens and move the camera
        //LatLng horsens = new LatLng(55.858131, 9.847588);
        //mMap.addMarker(new MarkerOptions().position(horsens).title("Marker in Horsens"));
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(@NonNull Marker marker) {

            }

            @Override
            public void onMarkerDragStart(@NonNull Marker marker) {

            }
        });
        //ArrayList<Event> events = new ArrayList<Event>();
        //events.add(new Event("going out", "just going out with few friends", "horsens", "me", 55.858141, 9.847580));
        //events.add(new Event("house party", "party lmao", "Aarhus", "not me", 55.958231, 9.947688));
        //events.add(new Event("going to restaurant", "me hungry me eat", "Vejle", "my friend", 55.858251, 9.848588));

        eventMarker = mMap.addMarker(
                new MarkerOptions()
                        .position(horsensLocation)
                        .draggable(true));

        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));  //zoom -> move to location... it doesn't work the other way idk why
        mMap.moveCamera(CameraUpdateFactory.newLatLng(horsensLocation));
    }

    public void selectLocation(View view)
    {
        eventMarker.getPosition();
        System.out.println(eventMarker.getPosition().toString());
        eventViewModel.getAddedEvent().getValue().setLatitude(eventMarker.getPosition().latitude);
        eventViewModel.getAddedEvent().getValue().setLongitude(eventMarker.getPosition().longitude);
        System.out.println(eventViewModel.getAddedEvent().getValue().getLatitude());
        System.out.println(eventViewModel.getAddedEvent().getValue().getLongitude());


        //AddEventFragment fragment = new AddEventFragment();
        //bundle.putDouble("latitude",0.0);
        //bundle.putDouble("longitude",0.0);
        //fragment.setArguments(bundle);
        finish();
    }
}