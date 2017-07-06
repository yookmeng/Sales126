package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.SpringMVC.model.Review;
import org.springframework.jdbc.core.RowMapper;
 
public class ReviewMapper implements RowMapper<Review> {
 
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int reviewid = rs.getInt("reviewid");
        String period = rs.getString("period");        
        int userid = rs.getInt("userid");        
        String username = rs.getString("username");        
        int teamid = rs.getInt("teamid");        
        String teamname = rs.getString("teamname");        
        int branchid = rs.getInt("branchid");        
        String branchname = rs.getString("branchname");        
        int companyid = rs.getInt("companyid");        
        String companyname = rs.getString("companyname");        
        int targetid = rs.getInt("targetid");        
        int teamtargetid = rs.getInt("teamtargetid");        
        Date reviewdate = rs.getDate("reviewdate");
        int prospect = rs.getInt("prospect");
        int testdrive = rs.getInt("testdrive");
        int closed = rs.getInt("closed");
        String minute = rs.getString("minute");
        int reviewby = rs.getInt("reviewby");
        String reviewbyname = rs.getString("reviewbyname");
        
        return new Review(reviewid, period, userid, username, teamid, teamname, 
        		branchid, branchname, companyid, companyname, 
        		targetid, teamtargetid, reviewdate, 
        		prospect, testdrive, closed, minute, 
        		reviewby, reviewbyname);
    }
}