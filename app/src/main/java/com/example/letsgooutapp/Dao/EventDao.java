package com.example.letsgooutapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.letsgooutapp.Model.Event;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void addNewEvent(Event event);

    @Query ("UPDATE event_table SET participants = :newParticipants WHERE id = :eventId")
    void updateParticipantsOfEvent(ArrayList<String> newParticipants, int eventId);

    @Query("SELECT participants FROM event_table WHERE id = :id")
    LiveData<String> getParticipantsByEventId(int id);

    @Query ("DELETE FROM event_table WHERE id = :id" )
    void deleteEventById(int id);

    @Query("SELECT * FROM event_table WHERE id = :id")
    LiveData<Event> getEventById(int id);

    @Query("SELECT * FROM event_table WHERE title = :title")
    LiveData<Event> getEventByTitle(String title);

    @Query("SELECT participants FROM event_table WHERE id = :id")
    LiveData<List<String>> getParticipantsByEventTitle(int id);

    @Query("SELECT * FROM event_table")
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT interests FROM event_table WHERE id = :id")
    LiveData<List<String>> getAllInterestsByEventId(int id);
}
