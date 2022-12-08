package com.cliff2.api;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PersonScheduleMapper implements RowMapper {

    @Override
    public PersonSchedule map(ResultSet rs, StatementContext ctx) throws SQLException {
        PersonSchedule schedule = new PersonSchedule();
        schedule.setPersonId(rs.getInt("person_id"));
        schedule.setTaskId(rs.getInt("task_id"));
//        schedule.setStartTime(LocalDateTime.parse(rs.getString("start_time")));
//        schedule.setEndTime(rs.getTimestamp("end_time"));
        return schedule;
    }
}
