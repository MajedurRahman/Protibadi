package com.nsu.protibadi.DatabaseHelper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.nsu.protibadi.Model.TrackPoint;

import java.util.List;

/**
 * Created by Majedur Rahman on 2/10/2018.
 */

@Dao
public interface TrackPointDAO {

    @Insert
    void insertTrackPointToDB(TrackPoint trackPoint);

    @Query("Select * from TrackPoint where TrackId= :id")
    List<TrackPoint> getAllTrackPointsByTrackID(int id);

}
