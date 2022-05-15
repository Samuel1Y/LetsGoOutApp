package com.example.letsgooutapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.letsgooutapp.Dao.InterestDao;
import com.google.firebase.database.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "interest_table")
public class Interest implements Serializable {


    @ColumnInfo(name = "interest")
    @NonNull
    private String interest;

    @ColumnInfo(name = "interestId")
    @PrimaryKey(autoGenerate = true)
    @NonNull private int interestId;

    public Interest(@NonNull String interest, int interestId)
    {
        this.interest = interest;
        this.interestId = interestId;
    }

    public Interest(@NonNull String interest)
    {
        this.interest = interest;
    }

    public Interest() {
        this.interest = null;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getInterestId() {
        return interestId;
    }

    public void setInterestId(int eventId) {
        this.interestId = eventId;
    }

}
