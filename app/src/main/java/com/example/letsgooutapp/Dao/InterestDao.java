package com.example.letsgooutapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.Interest;
import com.example.letsgooutapp.Repository.InterestRepository;

import java.util.List;
@Dao
public interface InterestDao {

    @Insert
    void addInterest(Interest interest);

    @Query("SELECT interestId FROM interest_table WHERE interest = :interest")
    LiveData<List<Integer>> getEventIdsByInterest(String interest);

    @Query("SELECT interest FROM interest_table WHERE interestId = :interestId")
    LiveData<List<String>> getInterestsByInterestId(int interestId);

    @Query("SELECT * FROM interest_table")
    LiveData<List<Interest>> getAllInterests();
}
