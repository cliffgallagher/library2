package com.cliff2.api;

import java.io.Serializable;

public class Person implements Serializable {

    private int externalId;
    private String name;

    public Person() {}

    public int getExternalId() {
        return externalId;
    }

    public String getName() {
        return name;
    }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    public void setName(String name) {
        this.name = name;
    }

}
