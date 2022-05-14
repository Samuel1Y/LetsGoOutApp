package com.example.letsgooutapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.Participant;

import java.util.List;
@Dao
public interface ParticipantDao {

    @Insert
    void addParticipant(Participant participant);

    @Query("SELECT eventId FROM participant_table WHERE participantUsername = :participant")
    LiveData<List<Integer>> getEventIdsByParticipant(String participant);

    @Query("SELECT participantUsername FROM participant_table WHERE eventId = :eventId")
    LiveData<List<String>> getParticipantUsernamesByEventId(int eventId);

    @Query("SELECT * FROM participant_table")
    LiveData<List<Participant>> getAllParticipants();
}
