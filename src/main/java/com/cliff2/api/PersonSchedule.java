package com.cliff2.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class PersonSchedule implements Serializable {

    private int externalId;
    private int personId;
    private String personName;
    private int taskId;
    private String taskDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss a") private ZonedDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss a") private ZonedDateTime endTime;

    public PersonSchedule() {}

    public int getExternalId() {
        return externalId;
    }
    public int getPersonId() {return personId;}
    public String getPersonName() {return personName;}
    public int getTaskId() {return taskId;}
    public String getTaskDescription() {return taskDescription;}
    public ZonedDateTime getStartTime() { return startTime; }
    public ZonedDateTime getEndTime() { return endTime; }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }
    public void setPersonId(int personId) {this.personId = personId;}
    public void setPersonName(String personName) {this.personName = personName;}
    public void setTaskId(int taskId) {this.taskId = taskId;}
    public void setTaskDescription(String taskDescription) {this.taskDescription = taskDescription;}
    public void setStartTime(ZonedDateTime startTime) { this.startTime = startTime; }
    public void setEndTime(ZonedDateTime endTime) { this.endTime = endTime; }

}
