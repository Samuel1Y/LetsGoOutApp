package com.example.letsgooutapp.Repository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.letsgooutapp.Dao.RegisterDao;
import com.example.letsgooutapp.Database.AppDatabase;
import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Interest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.transform.Result;

public class RegisterRepository {
    private MutableLiveData<Account> registeredUser;
    private RegisterDao registerDao;
    private static RegisterRepository instance;
    private ExecutorService executorService;
    private Handler mainThreadHandler;

    private RegisterRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        registerDao = database.registerDao();
        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
        registeredUser = new MutableLiveData<>();
        Account account = new Account();
        registeredUser.setValue(account);
    }

    public static synchronized RegisterRepository getInstance(Application application){
        if(instance == null){
            instance = new RegisterRepository(application);
        }
        return instance;
    }

    public void addNewAccount(Account account){
        executorService.execute(() ->  {
            registerDao.addNewAccount(account);
        });
        registeredUser.setValue(account);
    }

    public LiveData<Account> getRegisteredUser(){
        return registeredUser;
    }

    public LiveData<Account> getUser(String username){
        return registerDao.getUser(username);
    }

    public LiveData<List<Account>> getAllUser(){
        return registerDao.getAllUsers();
    }

    public void setRegisteredUser(Account account){
        registeredUser.setValue(account);
    }

    public void setInterests(ArrayList<Interest> interests){
        ArrayList<String> interestStrings = new ArrayList<>();
        for(Interest interest:interests) {
            interestStrings.add(String.valueOf(interest.getInterestId()));
        }
        executorService.execute(() ->  {
            registerDao.updateInterests(interestStrings, registeredUser.getValue().getUsername());
        });
    }

    public LiveData<List<String>> getAllInterests(){
        return registerDao.getInterestsByUsername(registeredUser.getValue().getUsername());
    }

}
