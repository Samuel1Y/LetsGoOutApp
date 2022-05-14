package com.example.letsgooutapp.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.letsgooutapp.Dao.EventDao;
import com.example.letsgooutapp.Dao.RegisterDao;
import com.example.letsgooutapp.Database.AppDatabase;
import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.transform.Result;

public class EventRepository {
    private MutableLiveData<Event> addedEvent;
    private EventDao eventDao;
    private static EventRepository instance;
    private ExecutorService executorService;
    private Handler mainThreadHandler;

    private EventRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        eventDao = database.eventDao();
        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        addedEvent = new MutableLiveData<>();
        Event event = new Event();
        addedEvent.setValue(event);
    }

    public static synchronized EventRepository getInstance(Application application){
        if(instance == null){
            instance = new EventRepository(application);
        }
        return instance;
    }

    public void addNewEvent(Event event){
        executorService.execute(() ->  {
            eventDao.addNewEvent(event);
        });
        addedEvent.setValue(event);
    }

    public void deleteEventById(int id){
        executorService.execute(() ->  {
            eventDao.deleteEventById(id);
        });
    }

    public LiveData<Event> getAddedEvent(){
        return addedEvent;
    }

    public LiveData<Event> getEventByTitle(String title){
        return eventDao.getEventByTitle(title);
    }

    public LiveData<Event> getEventById(int id){
        return eventDao.getEventById(id);
    }

    public void updateParticipantsOfEvent(ArrayList<String> newParticipants, int eventId)
    {
        executorService.execute(() ->  {
            eventDao.updateParticipantsOfEvent(newParticipants, eventId);
        });
    }

    public LiveData<List<Event>> getAllEvents(){
        return eventDao.getAllEvents();
    }
}
