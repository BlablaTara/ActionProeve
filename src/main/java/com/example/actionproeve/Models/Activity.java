package com.example.actionproeve.Models;

import java.util.List;

public class Activity {

    private String activityName;
    private List<String> durations;
    private String information;
    private String image;


    public Activity() {
    }

    public Activity(String activityName, List<String> durations, String information, String image) {
        this.activityName = activityName;
        this.durations = durations;
        this.information = information;
        this.image = image;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<String> getDurations() {
        return durations;
    }

    public void setDurations(List<String> durations) {
        this.durations = durations;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
