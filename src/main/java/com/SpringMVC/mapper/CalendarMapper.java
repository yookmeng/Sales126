package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.Calendar;
import org.springframework.jdbc.core.RowMapper;
 
public class CalendarMapper implements RowMapper<Calendar> {
 
    @Override
    public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String start = rs.getString("starts");
        String end = rs.getString("ends");
        String url = rs.getString("url");
        boolean allDay = rs.getBoolean("allDay");
        
        return new Calendar(id, title, start, end, url, allDay);
    }
}