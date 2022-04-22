package com.example.letsgooutapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Repository.RegisterRepository;

import java.util.List;

public class RegisterViewModel extends AndroidViewModel {
    private RegisterRepository repository;

    public RegisterViewModel(Application application){
        super(application);
        repository = RegisterRepository.getInstance(application);
    }

    public void addNewAccount(Account account){
        repository.addNewAccount(account);
    }

    public LiveData<Account> getRegisteredUser(){
        return repository.getRegisteredUser();
    }

    public LiveData<Account> getUser(String username){
        return repository.getUser(username);
    }

    public LiveData<List<Account>> getAllUser(){
        return repository.getAllUser();
    }
}
