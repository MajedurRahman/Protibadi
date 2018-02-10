package com.nsu.protibadi.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Majedur Rahman on 2/9/2018.
 */


@Entity
public class TrackPoint {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "TrackId")
    int trackID;

    @ColumnInfo(name = "Latitude")
    double latitude;
    @ColumnInfo(name = "Longitude")
    double longitude;
    @ColumnInfo(name = "Time")
    long time;

    public TrackPoint(double v, double v1, long l) {
    }

    public TrackPoint(double latitude, int trackID, double longitude, long time) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.trackID = trackID;
    }

    public int getId() {
        return id;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
