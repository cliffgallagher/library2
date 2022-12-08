package com.cliff2.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class PersonSchedule implements Serializable {

    @JsonProperty
    private int externalId;

    @JsonProperty
    private int personId;

    @JsonProperty
    private String personName;

    @JsonProperty
    private int taskId;

    @JsonProperty
    private String taskDescription;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss a") private LocalDateTime startTime;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss a") private LocalDateTime endTime;

    public PersonSchedule() {}

    public int getExternalId() {
        return externalId;
    }
    public int getPersonId() {return personId;}
    public String getPersonName() {return personName;}
    public int getTaskId() {return taskId;}
    public String getTaskDescription() {return taskDescription;}
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }
    public void setPersonId(int personId) {this.personId = personId;}
    public void setPersonName(String personName) {this.personName = personName;}
    public void setTaskId(int taskId) {this.taskId = taskId;}
    public void setTaskDescription(String taskDescription) {this.taskDescription = taskDescription;}
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

}
