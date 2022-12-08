package com.cliff2.resources;

import com.cliff2.api.Person;
import com.cliff2.api.PersonSchedule;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;

@Path("/personschedule")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonScheduleResource {
    private Jdbi jdbi;

    public PersonScheduleResource(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @GET
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
    public Response postPersonSchedule(PersonSchedule incomingBody) {
        System.out.println(incomingBody);
        return Response.ok(incomingBody).build();
//        Object[] array = incomingBody.values().toArray();
//        for (Object obj : array) {
//            System.out.println(obj.toString());
//        }
//        Integer incomingPersonId = Integer.parseInt((String)array[0]);
//        Integer incomingTaskId = Integer.parseInt((String)array[1]);;
//        LocalDateTime incomingStartDate = LocalDateTime.parse(array[2].toString());
//        LocalDateTime incomingEndDate = LocalDateTime.parse(array[3].toString());
//
//        System.out.println("startTime: " + incomingStartDate);
//
//        int result = jdbi.withHandle(handle -> {
//            return handle.execute("INSERT INTO person_schedules (person_id, task_id, start_time, end_time) VALUES (?, ?, ?, ?)", incomingPersonId, incomingTaskId, incomingStartDate, incomingEndDate);
//        });
//        return Response.status(Response.Status.fromStatusCode(201)).entity(result).build();
    }

    @DELETE
    public Response deletePersonSchedule(LinkedHashMap incomingBody) {
        Object[] array = incomingBody.values().toArray();

        for (Object obj : array) {
            System.out.println(obj.toString());
        }
        Integer incomingPersonId = (Integer) array[0];
//        return "DELETE PersonSchedule";
        jdbi.withHandle(handle -> {
            return handle.execute("DELETE FROM person_schedules WHERE external_id = ?", incomingPersonId);
        });

        return Response.status(Response.Status.fromStatusCode(204)).build();
    }

}

