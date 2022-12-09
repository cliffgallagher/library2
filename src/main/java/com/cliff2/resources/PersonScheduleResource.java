package com.cliff2.resources;

import com.cliff2.api.Person;
import com.cliff2.api.PersonSchedule;
import com.cliff2.helper.PersonScheduleHelper;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.Handle;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.cliff2.api.PersonScheduleMapper;

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
    public Response postPersonSchedule(PersonSchedule incomingPersonSchedule) {
        List<String> errorMessages = new ArrayList<>();

        Integer incomingPersonId = incomingPersonSchedule.getPersonId();
        Integer incomingTaskId = incomingPersonSchedule.getTaskId();
        LocalDateTime incomingStartTime = incomingPersonSchedule.getStartTime();
        LocalDateTime incomingEndTime = incomingPersonSchedule.getEndTime();

        if (incomingEndTime.isBefore(incomingStartTime)) {
            errorMessages.add("Start time must be before end time.");
            return Response.status(400).entity(errorMessages).build();
        }

        boolean isConflict = PersonScheduleHelper.isScheduleConflict(this.jdbi, incomingPersonSchedule);

        if (isConflict) {
            errorMessages.add("This person is already scheduled for a task during this time");
            return Response.status(409).entity(errorMessages).build();
        }

        PersonSchedule inserted = jdbi.registerRowMapper
                (PersonSchedule.class,
                        (rs, ctx) -> {
                            PersonSchedule schedule = new PersonSchedule();
                            schedule.setPersonId(rs.getInt("person_id"));
                            schedule.setTaskId(rs.getInt("task_id"));
                            // TODO return start date and end date in response
                            return schedule;
                        }
                ).withHandle(handle -> {
            return handle.createUpdate("INSERT INTO person_schedules (person_id, task_id, start_time, end_time) VALUES (?, ?, ?, ?)")
                    .bind(0, incomingPersonId)
                    .bind(1, incomingTaskId)
                    .bind(2, incomingStartTime)
                    .bind(3, incomingEndTime)
                    .executeAndReturnGeneratedKeys()
                    .mapTo(PersonSchedule.class)
                    .one();
        });
        return Response.status(Response.Status.fromStatusCode(201)).entity(inserted).build();

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

