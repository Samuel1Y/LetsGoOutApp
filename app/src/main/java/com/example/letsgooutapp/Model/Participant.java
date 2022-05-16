package com.example.letsgooutapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "participant_table", primaryKeys = {"eventId", "participantUsername"})
public class Participant implements Serializable {
    @ColumnInfo(name = "eventId")
    @NonNull
    private int eventId;

    @ColumnInfo(name = "participantUsername")
    @NonNull
    private String participantUsername;

    public Participant(@NotNull String participantUsername,@NotNull int eventId)
    {
        this.eventId = eventId;
        this.participantUsername = participantUsername;
    }

    public Participant()
    {
        this.eventId = -1;
        this.participantUsername = null;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getParticipantUsername() {
        return participantUsername;
    }

    public void setParticipantUsername(String participantUsername) {
        this.participantUsername = participantUsername;
    }
}
