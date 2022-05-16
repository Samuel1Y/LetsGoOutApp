package com.example.letsgooutapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.Participant;
import com.example.letsgooutapp.Repository.ParticipantRepository;

import java.util.List;

public class ParticipantViewModel extends AndroidViewModel {
    private ParticipantRepository repository;

    public ParticipantViewModel(Application application){
        super(application);
        repository = ParticipantRepository.getInstance(application);
    }

    void addParticipant(String participant, int eventId)
    {
        repository.addParticipant(participant, eventId);
    }

    LiveData<List<Integer>> getEventIdsByParticipant(String participant)
    {
        return repository.getEventIdsByParticipant(participant);
    }

    LiveData<List<String>> getParticipantUsernamesByEventId(int eventId)
    {
        return repository.getParticipantUsernamesByEventId(eventId);
    }

    LiveData<List<Participant>> getAllParticipants()
    {
        return repository.getAllParticipants();
    }
}
