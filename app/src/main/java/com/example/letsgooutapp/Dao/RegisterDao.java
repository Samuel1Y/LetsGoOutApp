package com.example.letsgooutapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.letsgooutapp.Model.Account;

public class RegisterDao {
    private MutableLiveData<Account> registerUser;
    private static RegisterDao instance;

    private RegisterDao(){
        registerUser = new MutableLiveData<>();
    }

    public static RegisterDao getInstance(){
        if(instance == null){
            instance = new RegisterDao();
        }
        return instance;
    }

    public void addNewAccount(Account account)
    {
        //add to database
        registerUser.setValue(account);
    }

    public LiveData<Account> getRegisteredUser(){
        return registerUser;
    }
}
