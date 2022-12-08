package com.cliff2.resources;

import com.cliff2.api.PersonSchedule;
import com.cliff2.api.Task;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/task")
public class TaskResource {
    private Jdbi jdbi;

    public TaskResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    @Produces("application/json")
    public Response getAllTasks() {

        List<Task> tasks = jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM \"tasks\" ORDER BY \"description\"")
                    .mapToBean(Task.class)
                    .list();
        });

        return Response.status(Response.Status.OK).entity(tasks).build();

    }

    @GET
    @Path("/unassigned")
    @Produces("application/json")
    public Response getUnassignedTasks() {

        List<Task> tasks = jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM \"tasks\" ORDER BY \"description\"")
                    .mapToBean(Task.class)
                    .list();
        });

        List<PersonSchedule> personSchedules = jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM \"person_schedules\" ORDER BY \"start_time\"")
                    .mapToBean(PersonSchedule.class)
                    .list();
        });

        List<Task> toRemove = new ArrayList<>();
        for (Task task: tasks) {
            for (PersonSchedule personSchedule: personSchedules) {
                if (task.getExternalId() == personSchedule.getTaskId()) {
                    toRemove.add(task);
                    break;
                }
            }
        }

        tasks.removeAll(toRemove);

        return Response.status(Response.Status.OK).entity(tasks).build();

    }

    @POST
    public String postTask() {
        return "POST Task";
    }
}

