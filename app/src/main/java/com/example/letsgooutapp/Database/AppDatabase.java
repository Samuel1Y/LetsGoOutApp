package com.example.letsgooutapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.letsgooutapp.Dao.EventDao;
import com.example.letsgooutapp.Dao.RegisterDao;
import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Event;

@Database(entities = {Account.class, Event.class}, version =1)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase instance;
    public abstract RegisterDao registerDao();
    public abstract EventDao eventDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
