package com.cliff2.api;

import java.io.Serializable;
import java.time.Instant;

public class Task implements Serializable {

    private int externalId;
    private String description;
    private Instant startTime;
    private Instant endTime;

    public Task() {}

    public int getExternalId() {
        return externalId;
    }

    public String getDescription() {
        return description;
    }

    public Instant getStartTime() { return startTime; }

    public Instant getEndTime() { return endTime; }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(Instant startTime) { this.startTime = startTime; }

    public void setEndTime(Instant endTime) { this.endTime = endTime; }

}
