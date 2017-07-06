package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.SpringMVC.model.SMSLog;
import org.springframework.jdbc.core.RowMapper;
 
public class SMSLogMapper implements RowMapper<SMSLog> {
 
    @Override
    public SMSLog mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int smsid = rs.getInt("smsid");
        int userid = rs.getInt("userid");
        String username = rs.getString("username");
        String usermobile = rs.getString("usermobile");
        int prospectid = rs.getInt("prospectid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String mobile = rs.getString("mobile");
        String message = rs.getString("message");
        Date datesend= rs.getDate("datesend");
        Time timesend = rs.getTime("timesend");
        
        return new SMSLog(smsid, userid, username, usermobile, 
        		prospectid, firstname, lastname, mobile, 
        		message, datesend, timesend);
    }
}