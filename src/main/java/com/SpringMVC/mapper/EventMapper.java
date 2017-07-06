package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.SpringMVC.model.Event;
import org.springframework.jdbc.core.RowMapper;
 
public class EventMapper implements RowMapper<Event> {
 
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int id = rs.getInt("id");
        int userid = rs.getInt("userid");
        int prospectid = rs.getInt("prospectid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String mobile = rs.getString("mobile");
        int activityid = rs.getInt("activityid");
        String brandname = rs.getString("brandname");
        String modelname = rs.getString("modelname");
        String quotationpdflink = rs.getString("quotationpdflink");
        String title = rs.getString("title");
        String remark = rs.getString("remark");
        String period = rs.getString("period");
        Date startdate = rs.getDate("startdate");
        Time starttime = rs.getTime("starttime");
    	String startdatetime = "";
        if (rs.getDate("startdate")==null){
        } else {
        	startdatetime = rs.getDate("startdate").toString() + " " + rs.getTime("starttime").toString();
        }
    	Date enddate = rs.getDate("enddate");
        Time endtime = rs.getTime("endtime");
        String enddatetime = "";
        if (rs.getDate("enddate")==null){        	
        } else {
        	enddatetime = rs.getDate("enddate").toString() + " " + rs.getDate("endtime").toString();
        }
    	String url = rs.getString("url");
        boolean allDay = rs.getBoolean("allDay");
        boolean status = rs.getBoolean("status");
        
        return new Event(id, userid, prospectid, firstname, lastname, mobile, 
        		activityid, brandname, modelname, quotationpdflink, 
        		title, remark, period,
        		startdate, starttime, startdatetime, 
        		enddate, endtime, enddatetime, 
        		url, allDay, status);
    }
}