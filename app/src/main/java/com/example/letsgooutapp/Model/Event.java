package com.example.letsgooutapp.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "event_table")
public class Event implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "creator")
    private String creator;

    @ColumnInfo(name = "latitude")
    private double latitude;

    @ColumnInfo(name = "longitude")
    private double longitude;

    @ColumnInfo(name = "participants")
    private ArrayList<String> participants;

    @ColumnInfo(name = "interests")
    private ArrayList<String> interests;

    //need participants but I guess we need another table for that
    //also idk if we want to have interests/themes for event or just accounts have those

    public Event(String title, String description, String location, String creator, Double latitude, Double longitude, ArrayList<String> participants, ArrayList<String> interests) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.creator = creator;
        this.latitude = latitude;
        this.longitude = longitude;
        this.participants = participants;
        this.interests = interests;
    }

    public Event(String title, String description, String location, String creator, Double latitude, Double longitude) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.creator = creator;
        this.latitude = latitude;
        this.longitude = longitude;
        this.participants = new ArrayList<String>();
        this.interests = new ArrayList<String>();
    }

    public Event(int id, String title, String description, String location, String creator, Double latitude, Double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.creator = creator;
        this.latitude = latitude;
        this.longitude = longitude;
        this.participants = new ArrayList<String>();
    }

    public Event() {
        this.title = null;
        this.description = null;
        this.location = null;
        this.creator = null;
        this.participants = new ArrayList<String>();
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }
    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }
}