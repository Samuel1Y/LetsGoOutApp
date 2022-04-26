package com.example.letsgooutapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "event_table")
public class Event implements Serializable {
    @PrimaryKey
    @NonNull
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "creator")
    private String creator; // or Account ? not sure which would be better

    //need participants but I guess we need another table for that
    //also idk if we want to have interests/themes for event or just accounts have those

    public Event(String title, String description, String location, String creator) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.creator = creator;
    }

    public Event() {
        this.title = null;
        this.description = null;
        this.location = null;
        this.creator = null;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getCreator() {
        return creator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}