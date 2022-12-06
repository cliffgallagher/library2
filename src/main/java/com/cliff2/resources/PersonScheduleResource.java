package com.cliff2.resources;

import com.cliff2.api.PersonSchedule;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;

@Path("/personschedule")
public class PersonScheduleResource {
    private Jdbi jdbi;

    public PersonScheduleResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
    @Produces("application/json")
    public Response getPersonSchedule() {

        List<PersonSchedule> personSchedules = jdbi.withHandle(handle -> {
            return handle.createQuery("SELECT * FROM \"person_schedules\" ORDER BY \"start_time\"")
                    .mapToBean(PersonSchedule.class)
                    .list();
        });

        personSchedules.forEach(personSchedule -> {

            //get personName of each personSchedule
            int personId = personSchedule.getPersonId();
            String personName = jdbi.withHandle(handle -> {
                return handle.select("SELECT name FROM person WHERE external_id = ?", personId)
                        .mapTo(String.class)
                        .one();
            });
            personSchedule.setPersonName(personName);

            //get taskDescription of each personSchedule
            int taskId = personSchedule.getTaskId();
            String taskDescription = jdbi.withHandle(handle -> {
                return handle.select("SELECT description FROM tasks WHERE external_id = ?", taskId)
                        .mapTo(String.class)
                        .one();
            });
            personSchedule.setTaskDescription(taskDescription);
        });

        return Response.status(Response.Status.OK).entity(personSchedules).build();

    }

    @POST
    public Response postPersonSchedule(LinkedHashMap incomingBody) {
        Object[] array = incomingBody.values().toArray();
        Integer incomingPersonId = (Integer) array[0];
        Integer incomingTaskId = (Integer) array[1];
        ZonedDateTime incomingStartDate = ZonedDateTime.parse(array[2].toString());
        ZonedDateTime incomingEndDate = ZonedDateTime.parse(array[3].toString());

        int result = jdbi.withHandle(handle -> {
            return handle.execute("INSERT INTO person_schedules (person_id, task_id, start_time, end_time) VALUES (?, ?, ?, ?)", incomingPersonId, incomingTaskId, incomingStartDate, incomingEndDate);
        });
        return Response.status(Response.Status.fromStatusCode(201)).entity(result).build();
    }

}

