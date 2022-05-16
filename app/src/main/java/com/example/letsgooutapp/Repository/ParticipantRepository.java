package com.example.letsgooutapp.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.provider.Telephony;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.letsgooutapp.Dao.InterestDao;
import com.example.letsgooutapp.Dao.ParticipantDao;
import com.example.letsgooutapp.Database.AppDatabase;
import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.Interest;
import com.example.letsgooutapp.Model.Participant;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParticipantRepository {
    private MutableLiveData<Participant> addedParticipant;
    private ParticipantDao participantDao;
    private static ParticipantRepository instance;
    private ExecutorService executorService;
    private Handler mainThreadHandler;

    private ParticipantRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        participantDao = database.participantDao();
        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        addedParticipant = new MutableLiveData<>();
        Participant participant = new Participant();
        addedParticipant.setValue(participant);
    }

    public static synchronized ParticipantRepository getInstance(Application application){
        if(instance == null){
            instance = new ParticipantRepository(application);
        }
        return instance;
    }

    public void addParticipant(String participant, int eventId)
    {
        Participant participant1 = new Participant(participant, eventId);
        executorService.execute(() ->  {
            participantDao.addParticipant(participant1);
        });
        addedParticipant.setValue(participant1);
    }

    public LiveData<List<Integer>> getEventIdsByParticipant(String participant)
    {
        return participantDao.getEventIdsByParticipant(participant);
    }

    public LiveData<List<String>> getParticipantUsernamesByEventId(int eventId)
    {
        return participantDao.getParticipantUsernamesByEventId(eventId);
    }

    public LiveData<List<Participant>> getAllParticipants()
    {
        return participantDao.getAllParticipants();
    }
}
