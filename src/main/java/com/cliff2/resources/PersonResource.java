package com.cliff2.resources;

import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/person")
public class PersonResource {
    private Jdbi jdbi;

    public PersonResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    public String getPerson() {
        return "Get Person";
    }

    @POST
    public String postPerson() {
        return "POST Person";
    }
}

