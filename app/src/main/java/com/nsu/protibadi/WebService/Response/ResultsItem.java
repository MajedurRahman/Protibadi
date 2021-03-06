package com.nsu.protibadi.WebService.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultsItem implements Serializable {

    @SerializedName("message_id")
    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return
                "ResultsItem{" +
                        "message_id = '" + messageId + '\'' +
                        "}";
    }
}