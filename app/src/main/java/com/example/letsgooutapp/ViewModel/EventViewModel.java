package com.example.letsgooutapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Repository.EventRepository;
import com.example.letsgooutapp.Repository.RegisterRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    public void setSelectedEventId(int id){
        repository.setSelectedId(id);
    }

    public LiveData<Integer> getSelectedEventId(){
        return repository.getSelectedId();
    }

    public void addEvent(Event event){
        repository.addNewEvent(event);
    }

    public void deleteEventById(int id) {
        repository.deleteEventById(id);
    }

    public LiveData<Event> getEventByTitle(String title){
        return repository.getEventByTitle(title);
    }

    public LiveData<Event> getEventById(int id){
        return repository.getEventById(id);
    }

    public void updateParticipantsOfEvent(ArrayList<String> newParticipants, int eventId)
    {
        repository.updateParticipantsOfEvent(newParticipants, eventId);
    }

    public LiveData<String> getParticipantsByEventId(int id)
    {
        return repository.getParticipantsByEventId(id);
    }

    public LiveData<List<Event>> getAllEvents(){
        return repository.getAllEvents();
    }

    public LiveData<List<String>> getAllInterestsById(int id){
        return repository.getAllInterestsById(id);
    }

}
