package com.cliff2.helper;

import com.cliff2.api.Person;
import com.cliff2.api.PersonSchedule;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class PersonScheduleHelper {

    public static boolean isScheduleConflict(Jdbi jdbi, PersonSchedule incomingPersonSchedule) {

        //get personId of incomingPersonSchedule
        int personId = incomingPersonSchedule.getPersonId();

        //get that person's existing PersonSchedules
        List<PersonSchedule> personSchedules = jdbi.withHandle(handle -> {
            return handle.select("SELECT * FROM person_schedules WHERE person_id = ?", personId)
                    .mapToBean(PersonSchedule.class)
                    .list();
        });

        //if there is a conflicting schedule, return true. Otherwise, return false
        for (PersonSchedule personSchedule : personSchedules) {
            if (incomingPersonSchedule.getEndTime().isAfter(personSchedule.getStartTime()) && incomingPersonSchedule.getStartTime().isBefore(personSchedule.getEndTime())) {
                return true;
            }
        }
        return false;
    }

}
