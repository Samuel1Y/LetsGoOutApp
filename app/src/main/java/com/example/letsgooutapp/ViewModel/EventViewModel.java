package com.example.letsgooutapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Repository.EventRepository;
import com.example.letsgooutapp.Repository.RegisterRepository;

import java.util.ArrayList;
import java.util.List;

public class EventViewModel extends AndroidViewModel {
    private EventRepository repository;

    public EventViewModel(Application application){
        super(application);
        repository = EventRepository.getInstance(application);
    }

    public LiveData<Event> getAddedEvent(){
        return repository.getAddedEvent();
    }


    public void addEvent(Event event){
        repository.addNewEvent(event);
    }

    public void deleteEventById(int id) {
        repository.deleteEventById(id);
    }

    public void updateParticipantsOfEvent(ArrayList<String> newParticipants, int eventId)
    {
        repository.updateParticipantsOfEvent(newParticipants, eventId);
    }

    public LiveData<List<Event>> getAllEvents(){
        return repository.getAllEvents();
    }
}
