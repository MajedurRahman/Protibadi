package com.nsu.protibadi.DatabaseHelper;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.nsu.protibadi.Model.TrackPoint;

/**
 * Created by Majedur Rahman on 2/10/2018.
 */

@Database(entities = {TrackPoint.class}, version = 1)
public abstract class ProtibadiDB extends RoomDatabase{
    public abstract TrackPointDAO TrackPointService();
}
