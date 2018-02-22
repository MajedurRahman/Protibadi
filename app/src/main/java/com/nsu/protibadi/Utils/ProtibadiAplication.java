package com.nsu.protibadi.Utils;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;
import com.nsu.protibadi.DatabaseHelper.ProtibadiDB;

/**
 * Created by Majedur Rahman on 2/10/2018.
 */

public class ProtibadiAplication extends Application {

    private static final String DATABASE_NAME = "Protibadi";
    static RoomDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = Room.databaseBuilder(getApplicationContext(), ProtibadiDB.class, DATABASE_NAME).build();
    }

    public static RoomDatabase getDatabase() {
        return database;
    }
}
