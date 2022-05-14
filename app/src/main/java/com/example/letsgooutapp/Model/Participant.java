package com.example.letsgooutapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "participant_table", primaryKeys = {"eventId", "participantUsername"}
       /* foreignKeys = {@ForeignKey(entity = parentClass.class,
                parentColumns = "parrentClasscolumn",
                childColumns = "childClassColumn",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Participant.class,
                parentColumns = "parentClassColumn",
                childColumns = "childClassColumn",
                onDelete = ForeignKey.CASCADE)}*/)// idfk XDXDXD
public class Participant implements Serializable {
//SOMEHOW MAKE FOREIGN KEY ?!?!?!?!?
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
