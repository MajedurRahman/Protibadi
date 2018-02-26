package com.nsu.protibadi.Model;

/**
 * Created by Majedur Rahman on 2/25/2018.
 */

public class LinkModel {
    private String linkerId;
    private String linkWithName;
    private String joinWithName;
    private String joinerId;
    private boolean confermation;


    public LinkModel() {
    }

    public LinkModel(String linkerId, String linkWithName) {
        this.linkerId = linkerId;
        this.linkWithName = linkWithName;
    }

    public LinkModel(String linkerId, String linkWithName, String joinWithName, String joinerId, boolean confermation) {
        this.linkerId = linkerId;
        this.linkWithName = linkWithName;
        this.joinWithName = joinWithName;
        this.joinerId = joinerId;
        this.confermation = confermation;
    }

    public String getLinkerId() {
        return linkerId;
    }

    public void setLinkerId(String linkerId) {
        this.linkerId = linkerId;
    }

    public String getJoinerId() {
        return joinerId;
    }

    public void setJoinerId(String joinerId) {
        this.joinerId = joinerId;
    }

    public String getJoinWithName() {
        return joinWithName;
    }

    public void setJoinWithName(String joinWithName) {
        this.joinWithName = joinWithName;
    }

    public String getId() {
        return linkerId;
    }

    public void setId(String id) {
        this.linkerId = id;
    }

    public boolean isConfermation() {
        return confermation;
    }

    public void setConfermation(boolean confermation) {
        this.confermation = confermation;
    }

    public String getLinkWithName() {
        return linkWithName;
    }

    public void setLinkWithName(String linkWithName) {
        this.linkWithName = linkWithName;
    }
}
