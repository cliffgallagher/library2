package com.cliff2.resources;

import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/personschedule")
public class PersonScheduleResource {
    private Jdbi jdbi;

    public PersonScheduleResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    @Produces("application/json")
    public String getPersonSchedule() {

        return "GET PersonSchedule";

    }
//    @GET
//    @Produces("application/json")
//    public Response getTask() {
//
//        List<Task> tasks = jdbi.withHandle(handle -> {
//            return handle.createQuery("SELECT * FROM \"tasks\" ORDER BY \"description\"")
//                    .mapToBean(Task.class)
//                    .list();
//        });
//
//        return Response.status(Response.Status.OK).entity(tasks).build();
//
//    }

}

