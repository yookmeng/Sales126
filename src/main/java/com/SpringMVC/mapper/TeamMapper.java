package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Team;
import org.springframework.jdbc.core.RowMapper;
 
public class TeamMapper implements RowMapper<Team> {
 
    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int teamid = rs.getInt("teamid");
        String teamname = rs.getString("teamname");
        int branchid = rs.getInt("branchid");
        int leaderid = rs.getInt("leaderid");
        String leadername = rs.getString("leadername");
        
        return new Team(teamid, teamname, branchid, leaderid, leadername);
    }
}