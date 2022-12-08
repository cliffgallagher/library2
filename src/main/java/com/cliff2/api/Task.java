package com.cliff2.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Task implements Serializable {

    private int externalId;
    private String description;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss a") private ZonedDateTime startTime;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss a") private ZonedDateTime endTime;

    public Task() {}

    public int getExternalId() {
        return externalId;
    }

    public String getDescription() {
        return description;
    }

//    public ZonedDateTime getStartTime() { return startTime; }
//
//    public ZonedDateTime getEndTime() { return endTime; }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void setStartTime(ZonedDateTime startTime) { this.startTime = startTime; }
//
//    public void setEndTime(ZonedDateTime endTime) { this.endTime = endTime; }

}
