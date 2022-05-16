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
        Interest interest = new Interest("Sports",0);
        Interest interest1 = new Interest("Fashion",1);
        Interest interest2 = new Interest("IT",2);
        Interest interest3 = new Interest("Photography",3);
        Interest interest4 = new Interest("Art",4);
        Interest interest5 = new Interest("Music",5);
        interests.add(interest);
        interests.add(interest1);
        interests.add(interest2);
        interests.add(interest3);
        interests.add(interest4);
        interests.add(interest5);

        toSend.setValue(interests);

        return toSend;
        //return repository.getAllInterests();
    }
}
