package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;

import com.SpringMVC.model.Notes;
import org.springframework.jdbc.core.RowMapper;
 
public class NotesMapper implements RowMapper<Notes> {
 
    @Override
    public Notes mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int noteid = rs.getInt("noteid");
        Date notedate = rs.getDate("notedate");
        Time notetime = rs.getTime("notetime");
        int userid = rs.getInt("userid");        
        String username = rs.getString("username");        
        int teamid = rs.getInt("teamid");        
        String teamname = rs.getString("teamname");        
        int branchid = rs.getInt("branchid");        
        String branchname = rs.getString("branchname");        
        int companyid = rs.getInt("companyid");        
        String companyname = rs.getString("companyname");
        int prospectid = rs.getInt("prospectid");        
        String prospectname = rs.getString("prospectname");
        String note = rs.getString("note");
        int status = rs.getInt("status");
        String remark = rs.getString("remark");
        boolean read = rs.getBoolean("read");
        int reviewby = rs.getInt("reviewby");
        String reviewbyname = rs.getString("reviewbyname");
        Date reviewdate = rs.getDate("reviewdate");
        Time reviewtime = rs.getTime("reviewtime");
        
        return new Notes(noteid, notedate, notetime, userid, username, teamid, teamname, 
        		branchid, branchname, companyid, companyname, 
        		prospectid, prospectname, note, status, remark, read,
        		reviewby, reviewbyname, reviewdate, reviewtime);
    }
}