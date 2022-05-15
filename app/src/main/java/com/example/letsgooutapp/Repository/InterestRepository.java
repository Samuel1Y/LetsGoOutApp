package com.example.letsgooutapp.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Query;

import com.example.letsgooutapp.Dao.EventDao;
import com.example.letsgooutapp.Dao.InterestDao;
import com.example.letsgooutapp.Database.AppDatabase;
import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.Interest;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InterestRepository {
    private MutableLiveData<Interest> addedInterest;
    private InterestDao interestDao;
    private static InterestRepository instance;
    private ExecutorService executorService;
    private Handler mainThreadHandler;

    private InterestRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        interestDao = database.interestDao();
        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        addedInterest = new MutableLiveData<>();
        Interest interest = new Interest();
        addedInterest.setValue(interest);
    }

    public static synchronized InterestRepository getInstance(Application application){
        if(instance == null){
            instance = new InterestRepository(application);
        }
        return instance;
    }

    public void addInterest(String interest, int eventId){
        Interest interest1 = new Interest(interest,eventId);
        executorService.execute(() ->  {
            interestDao.addInterest(interest1);
        });
        addedInterest.setValue(interest1);
    }

    public LiveData<Interest> getAddedInterest(){
        return addedInterest;
    }

    public LiveData<List<Integer>> getEventIdsByInterest(String interest) // GETTING IDs NOT EVENTS
    {
        return interestDao.getEventIdsByInterest(interest);
    }

    public LiveData<List<String>> getInterestsByEventId(int eventId)
    {
        return interestDao.getInterestsByInterestId(eventId);
    }

    public LiveData<List<Interest>> getAllInterests()
    {
        return interestDao.getAllInterests();
    }
}
