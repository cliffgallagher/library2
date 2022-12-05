package com.cliff2.resources;

import com.cliff2.api.Person;
import com.cliff2.api.Task;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/task")
public class TaskResource {
    private Jdbi jdbi;

    public TaskResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    @Produces("application/json")
    public Response getTask() {

        List<Task> tasks = jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM \"tasks\" ORDER BY \"description\"")
                    .mapToBean(Task.class)
                    .list();
        });

        return Response.status(Response.Status.OK).entity(tasks).build();

    }

    @POST
    public String postTask() {
        return "POST Task";
    }
}

