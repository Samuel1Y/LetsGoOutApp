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
        /*Interest interest = new Interest("Sports",0);
        Interest interest1 = new Interest("Fashion",1);
        Interest interest2 = new Interest("IT",2);*/

        interests.add(new Interest("Sports", 0));
        interests.add(new Interest("Fashion", 1));
        interests.add(new Interest("IT", 2));
        interests.add(new Interest("Music", 3));
        interests.add(new Interest("Anime", 4));
        interests.add(new Interest("Gaming", 5));
        interests.add(new Interest("Art", 6));
        interests.add(new Interest("Reading", 7));

        toSend.setValue(interests);

        return toSend;
        //return repository.getAllInterests();
    }
}
