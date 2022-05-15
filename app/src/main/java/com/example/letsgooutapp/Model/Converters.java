package com.example.letsgooutapp.Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> stringArray = gson.fromJson(value, listType);
        return stringArray;
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}