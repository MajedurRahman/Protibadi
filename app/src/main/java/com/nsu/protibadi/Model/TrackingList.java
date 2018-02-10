package com.nsu.protibadi.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Majedur Rahman on 2/9/2018.
 */

public class TrackingList {
    public String trackId;
    public List<LatLng> positionList;
    public long startDateTime;
    public long endDateTime;

    public TrackingList(String trackId, long startDateTime) {
        this.trackId = trackId;
        this.startDateTime = startDateTime;
    }

    public TrackingList() {
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public List<LatLng> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<LatLng> positionList) {
        this.positionList = positionList;
    }

    public long getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(long startDateTime) {
        this.startDateTime = startDateTime;
    }

    public long getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(long endDateTime) {
        this.endDateTime = endDateTime;
    }
}