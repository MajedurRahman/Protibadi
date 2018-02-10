package com.nsu.protibadi.Model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Majedur Rahman on 2/10/2018.
 */

public class CustomLatLng implements Serializable {
    public long time;
    public double lat;
    public double lng;

    public CustomLatLng(long time, double lat, double lng) {
        this.time = time;
        this.lat = lat;
        this.lng = lng;
    }

    public CustomLatLng() {
    }

    public long gettime() {
        return time;
    }

    public void settime(long time) {
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return  time + " " + lat + " " + lng;
    }
}
