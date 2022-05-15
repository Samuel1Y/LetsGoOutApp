package com.example.letsgooutapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "account_table")
public class Account implements Serializable {
    @PrimaryKey @NonNull private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "interests")
    private ArrayList<String> interests;

    public Account(@NonNull String username, String email, String password)
    {
        this.email = email;
        this.username = username;
        this.password = password;
        this.interests = new ArrayList<>();
    }
    public Account(@NonNull String username, String email, String dateOfBirth, String password, ArrayList<String> interests)
    {
        this.email = email;
        this.username = username;
        this.password = password;
        this.interests = interests;
    }

    public Account(){
        this.email = null;
        this.username = null;
        this.password = null;
    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public void addInterest(Interest interest){
        this.interests.add(String.valueOf(interest.getInterestId()));
    }
}
