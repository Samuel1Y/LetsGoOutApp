package com.example.letsgooutapp.Repository;

import androidx.lifecycle.LiveData;

import com.example.letsgooutapp.Dao.RegisterDao;
import com.example.letsgooutapp.Model.Account;

public class RegisterRepository {
    private RegisterDao registerDao;
    private static RegisterRepository instance;

    private RegisterRepository(){
        registerDao = RegisterDao.getInstance();
    }

    public static RegisterRepository getInstance(){
        if(instance == null){
            instance = new RegisterRepository();
        }
        return instance;
    }

    public void addNewAccount(Account account){
        registerDao.addNewAccount(account);
    }

    public LiveData<Account> getRegisteredUser(){
        return registerDao.getRegisteredUser();
    }
}
