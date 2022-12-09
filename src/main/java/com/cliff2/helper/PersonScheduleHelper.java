package com.cliff2.helper;

import com.cliff2.api.Person;
import com.cliff2.api.PersonSchedule;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class PersonScheduleHelper {

    public static boolean isScheduleConflict(Jdbi jdbi, PersonSchedule incomingPersonSchedule) {

        //get personId of incomingPersonSchedule
        int personId = incomingPersonSchedule.getPersonId();

        List<PersonSchedule> personSchedules = jdbi.withHandle(handle -> {
            return handle.select("SELECT * FROM person_schedules WHERE person_id = ?", personId)
                    .mapToBean(PersonSchedule.class)
                    .list();
        });

        for (PersonSchedule personSchedule : personSchedules) {
            System.out.println(personSchedule.getTaskId());
        }
        return true;
    }

}
