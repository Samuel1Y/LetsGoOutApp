package com.example.letsgooutapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.letsgooutapp.Dao.EventDao;
import com.example.letsgooutapp.Dao.InterestDao;
import com.example.letsgooutapp.Dao.ParticipantDao;
import com.example.letsgooutapp.Dao.RegisterDao;
import com.example.letsgooutapp.Model.Account;
import com.example.letsgooutapp.Model.Converters;
import com.example.letsgooutapp.Model.Event;
import com.example.letsgooutapp.Model.Interest;
import com.example.letsgooutapp.Model.Participant;

@Database(entities = {Account.class, Event.class, Participant.class, Interest.class}, version =12)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{

    private static AppDatabase instance;
    public abstract RegisterDao registerDao();
    public abstract EventDao eventDao();
    public abstract InterestDao interestDao();
    public abstract ParticipantDao participantDao();

    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
