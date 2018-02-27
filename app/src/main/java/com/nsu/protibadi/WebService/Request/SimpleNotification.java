package com.nsu.protibadi.WebService.Request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SimpleNotification implements Serializable {

    @SerializedName("data")
    private Data data;

    @SerializedName("to")
    private String to;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return
                "SimpleNotification{" +
                        "data = '" + data + '\'' +
                        ",to = '" + to + '\'' +
                        "}";
    }
}