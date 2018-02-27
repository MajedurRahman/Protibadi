package com.nsu.protibadi.WebService.Response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SimpleResponse implements Serializable {

    @SerializedName("canonical_ids")
    private int canonicalIds;

    @SerializedName("success")
    private int success;

    @SerializedName("failure")
    private int failure;

    @SerializedName("results")
    private List<ResultsItem> results;

    @SerializedName("multicast_id")
    private long multicastId;

    public int getCanonicalIds() {
        return canonicalIds;
    }

    public void setCanonicalIds(int canonicalIds) {
        this.canonicalIds = canonicalIds;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public List<ResultsItem> getResults() {
        return results;
    }

    public void setResults(List<ResultsItem> results) {
        this.results = results;
    }

    public long getMulticastId() {
        return multicastId;
    }

    public void setMulticastId(long multicastId) {
        this.multicastId = multicastId;
    }

    @Override
    public String toString() {
        return
                "SimpleResponse{" +
                        "canonical_ids = '" + canonicalIds + '\'' +
                        ",success = '" + success + '\'' +
                        ",failure = '" + failure + '\'' +
                        ",results = '" + results + '\'' +
                        ",multicast_id = '" + multicastId + '\'' +
                        "}";
    }
}