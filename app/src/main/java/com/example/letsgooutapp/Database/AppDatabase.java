package com.example.letsgooutapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.letsgooutapp.Dao.RegisterDao;
import com.example.letsgooutapp.Model.Account;

@Database(entities = {Account.class}, version =1)
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase instance;
    public abstract RegisterDao registerDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
