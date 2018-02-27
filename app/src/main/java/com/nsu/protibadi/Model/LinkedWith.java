package com.nsu.protibadi.Model;

/**
 * Created by Majedur Rahman on 2/27/2018.
 */

public class LinkedWith {
    private String name;
    private String id;
    private long time;
    private String info;

    public LinkedWith() {
    }

    public LinkedWith(String name, String id, long time, String info) {
        this.name = name;
        this.id = id;
        this.time = time;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
