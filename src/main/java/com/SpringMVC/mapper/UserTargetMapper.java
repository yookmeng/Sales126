package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.UserTarget;
import org.springframework.jdbc.core.RowMapper;
 
public class UserTargetMapper implements RowMapper<UserTarget> {
 
    @Override
    public UserTarget mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int targetid = rs.getInt("targetid");
        int userid = rs.getInt("userid");
        String username = rs.getString("username");
        String period = rs.getString("period");
        int teamtargetid = rs.getInt("teamtargetid");
        int prospect = rs.getInt("prospect");
        int testdrive = rs.getInt("testdrive");
        int closed = rs.getInt("closed");
        
        return new UserTarget(targetid, userid, username, period, 
        		teamtargetid, prospect, testdrive, closed);
    }
}