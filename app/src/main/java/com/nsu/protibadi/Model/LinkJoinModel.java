package com.nsu.protibadi.Model;

/**
 * Created by Majedur Rahman on 2/26/2018.
 */

public class LinkJoinModel {
    private String link_with;
    private String join_with;
    private String linkerName;
    private String joinerName;
    private long timeData;

    public LinkJoinModel(String link_with, String join_with, String linkerName, String joinerName, long timeData) {
        this.link_with = link_with;
        this.join_with = join_with;
        this.linkerName = linkerName;
        this.joinerName = joinerName;
        this.timeData = timeData;
    }

    public LinkJoinModel() {
    }

    public String getLink_with() {
        return link_with;
    }

    public void setLink_with(String link_with) {
        this.link_with = link_with;
    }

    public String getJoin_with() {
        return join_with;
    }

    public void setJoin_with(String join_with) {
        this.join_with = join_with;
    }

    public String getLinkerName() {
        return linkerName;
    }

    public void setLinkerName(String linkerName) {
        this.linkerName = linkerName;
    }

    public String getJoinerName() {
        return joinerName;
    }

    public void setJoinerName(String joinerName) {
        this.joinerName = joinerName;
    }

    public long getTimeData() {
        return timeData;
    }

    public void setTimeData(long timeData) {
        this.timeData = timeData;
    }
}
