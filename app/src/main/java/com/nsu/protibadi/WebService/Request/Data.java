package com.nsu.protibadi.WebService.Request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data implements Serializable {

    @SerializedName("Message")
    private String message;

    @SerializedName("CustomData")
    private String customData;

    @SerializedName("Title")
    private String title;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return
                "Data{" +
                        "message = '" + message + '\'' +
                        ",customData = '" + customData + '\'' +
                        ",title = '" + title + '\'' +
                        "}";
    }
}