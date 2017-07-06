package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.State;
import org.springframework.jdbc.core.RowMapper;
 
public class StateMapper implements RowMapper<State> {
 
    @Override
    public State mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int stateid = rs.getInt("stateid");
        int countryid = rs.getInt("countryid");
        String countryname = rs.getString("countryname");
        String statename = rs.getString("statename");
        
        return new State(stateid, countryid, countryname, statename);
    }
}