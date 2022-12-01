package com.cliff2.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/person")
public class PersonResource {

    @GET
    public String getPerson() {
        return "Get Person";
    }

    @POST
    public String postPerson() {
        return "POST Person";
    }
}

