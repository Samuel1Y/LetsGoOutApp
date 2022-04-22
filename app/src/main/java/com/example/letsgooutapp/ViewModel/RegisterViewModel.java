package com.example.letsgooutapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Repository.RegisterRepository;

public class RegisterViewModel extends ViewModel {
    private RegisterRepository repository;

    public RegisterViewModel(){
        repository = RegisterRepository.getInstance();
    }

    public void addNewAccount(Account account){
        repository.addNewAccount(account);
    }

    public LiveData<Account> getRegisteredUser(){
        return repository.getRegisteredUser();
    }
}
