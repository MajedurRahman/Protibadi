package com.nsu.protibadi.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Majedur Rahman on 2/9/2018.
 */

public class User {

    public String userId;
    public String userName;
    public LatLng currentPosition;
    public List<TrackingList> trackingList;

    public User() {
    }

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LatLng getCurrentPossition() {
        return currentPosition;
    }

    public void setCurrentPossition(LatLng current) {
        currentPosition = current;
    }

    public List<TrackingList> getTrackingList() {
        return trackingList;
    }

    public void setTrackingList(List<TrackingList> trackingList) {
        this.trackingList = trackingList;
    }
}

