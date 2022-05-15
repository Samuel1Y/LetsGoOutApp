package com.example.letsgooutapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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
    public LiveData<List<Interest>> getAllInterests()
    {
        MutableLiveData<List<Interest>> toSend = new MutableLiveData<>();
        ArrayList<Interest> interests = new ArrayList<>();
        Interest interest = new Interest("Sports");
        Interest interest1 = new Interest("Fashion");
        Interest interest2 = new Interest("IT");
        interests.add(interest);
        interests.add(interest1);
        interests.add(interest2);
        toSend.setValue(interests);

        return toSend;
        //return repository.getAllInterests();
    }
}
