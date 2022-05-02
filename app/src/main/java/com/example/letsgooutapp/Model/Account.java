package com.example.letsgooutapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "account_table")
public class Account implements Serializable {
    @PrimaryKey @NonNull private String username;
    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "dateOfBirth")
    private String dateOfBirth;

    @ColumnInfo(name = "email")
    private String email;

    public Account(@NonNull String username, String email, String dateOfBirth, String password)
    {
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
    }

    public Account(){
        this.email = null;
        this.dateOfBirth = null;
        this.username = null;
        this.password = null;
    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
