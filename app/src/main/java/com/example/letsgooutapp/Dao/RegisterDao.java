package com.example.letsgooutapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.letsgooutapp.Model.Account;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RegisterDao {

    @Insert
    void addNewAccount(Account account);

    @Query("SELECT * FROM account_table WHERE username = :FindUsername")
    LiveData<Account> getUser(String FindUsername);

    @Query("SELECT * FROM account_table")
    LiveData<List<Account>> getAllUsers();

    @Query ("UPDATE account_table SET interests = :newInterests WHERE username = :usernameFrom")
    void updateInterests(ArrayList<String> newInterests, String usernameFrom);

    @Query("SELECT interests FROM account_table WHERE username = :usernameFrom")
    LiveData<List<String>> getInterestsByUsername(String usernameFrom);
}
