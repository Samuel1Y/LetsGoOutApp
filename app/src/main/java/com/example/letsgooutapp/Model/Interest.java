package com.example.letsgooutapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.letsgooutapp.Dao.InterestDao;
import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "interest_table", primaryKeys = {"interest", "eventId"})
public class Interest implements Serializable {


    @ColumnInfo(name = "interest")
    @NonNull
    private String interest;

    @ColumnInfo(name = "eventId")
    @NonNull private int eventId;

    public Interest(@NotNull String interest,@NotNull int eventId)
    {
        this.interest = interest;
        this.eventId = eventId;
    }

    public Interest()
    {
        this.interest = null;
        this.eventId = -1;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

}
