package com.example.letsgooutapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.letsgooutapp.Model.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void addNewEvent(Event event);

    @Query("SELECT * FROM event_table WHERE id = :id")
    LiveData<Event> getEventById(int id);

    @Query("SELECT * FROM event_table WHERE id = :title")
    LiveData<Event> getEventByTitle(String title);

    @Query("SELECT * FROM event_table")
    LiveData<List<Event>> getAllEvents();
}
