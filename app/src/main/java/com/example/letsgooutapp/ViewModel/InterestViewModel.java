package com.example.letsgooutapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.Interest;
import com.example.letsgooutapp.Repository.EventRepository;
import com.example.letsgooutapp.Repository.InterestRepository;

import java.util.ArrayList;
import java.util.List;

public class InterestViewModel extends AndroidViewModel {
    private InterestRepository repository;

    public InterestViewModel(Application application){
        super(application);
        repository = InterestRepository.getInstance(application);
    }

    public void addInterest(String interest, int eventId)
    {
        repository.addInterest(interest, eventId);
    }

    public LiveData<Interest> getAddedInterest(){
        return repository.getAddedInterest();
    }

    public LiveData<List<Integer>> getEventIdsByInterest(String interest)
    {
        return repository.getEventIdsByInterest(interest);
    }

    public LiveData<List<String>> getInterestsByEventId(int eventId)
    {
        return repository.getInterestsByEventId(eventId);
    }

    public LiveData<List<Interest>> getAllInterests()
    {
        return repository.getAllInterests();
    }
}
