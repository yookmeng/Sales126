package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.UserMonthlySummary;
import org.springframework.jdbc.core.RowMapper;
 
public class UserMonthlySummaryMapper implements RowMapper<UserMonthlySummary> {
 
    @Override
    public UserMonthlySummary mapRow(ResultSet rs, int rowNum) throws SQLException { 
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
        int targetprospect = rs.getInt("targetprospect");
        int targettestdrive = rs.getInt("targettestdrive");
        int targetclosed = rs.getInt("targetclosed");
        int actualprospect = rs.getInt("actualprospect");
        int actualtestdrive = rs.getInt("actualtestdrive");
        int actualclosed = rs.getInt("actualclosed");
        float percentprospect = rs.getFloat("percentprospect");
        float percenttestdrive = rs.getFloat("percenttestdrive");
        float percentclosed = rs.getFloat("percentclosed");
        float commission = rs.getFloat("commission");
        int totalhot = rs.getInt("totalhot");
        int pendingactivity = rs.getInt("pendingactivity");
        
        return new UserMonthlySummary(period, userid, username, 
        		teamid, teamname, branchid, branchname, companyid, companyname,
        		targetid, targetprospect, targettestdrive, targetclosed,
        		actualprospect, actualtestdrive, actualclosed,
        		percentprospect, percenttestdrive, percentclosed,
        		commission, totalhot, pendingactivity);
    }
}