package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;

import com.SpringMVC.model.Activity;
import org.springframework.jdbc.core.RowMapper;
 
public class ActivityMapper implements RowMapper<Activity> {
 
    @Override
    public Activity mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int activityid = rs.getInt("activityid");
        int userid = rs.getInt("userid");        
        String username = rs.getString("username");        
        int prospectid = rs.getInt("prospectid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        Date activitydate = rs.getDate("activitydate");
        Time activitytime = rs.getTime("activitytime");
        int brandid = rs.getInt("brandid");
        String brandname = rs.getString("brandname");
        int modelid = rs.getInt("modelid");
        String modelname = rs.getString("modelname");
        boolean demo = rs.getBoolean("demo");
        Date demodate = rs.getDate("demodate");
        Time demotime = rs.getTime("demotime");
        boolean testdrive = rs.getBoolean("testdrive");
        Date testdrivedate = rs.getDate("testdrivedate");
        Time testdrivetime = rs.getTime("testdrivetime");
        boolean quotation = rs.getBoolean("quotation");
        Date quotationdate = rs.getDate("quotationdate");
        Time quotationtime = rs.getTime("quotationtime");
        int quotationid = rs.getInt("quotationid");
        String quotationpdflink = rs.getString("quotationpdflink");
        boolean closed = rs.getBoolean("closed");
        Date closeddate = rs.getDate("closeddate");
        Time closedtime = rs.getTime("closedtime");
        
        return new Activity(activityid, userid, username, 
        		prospectid, firstname, lastname,
        		activitydate, activitytime, 
        		brandid, brandname, modelid, modelname,
        		demo, demodate, demotime, 
        		testdrive, testdrivedate, testdrivetime,
        		quotation, quotationdate, quotationtime,
        		quotationid, quotationpdflink,
        		closed, closeddate, closedtime);
    }
}