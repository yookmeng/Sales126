package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.SpringMVC.model.ClosingPeriod;
import org.springframework.jdbc.core.RowMapper;
 
public class ClosingPeriodMapper implements RowMapper<ClosingPeriod> {
 
    @Override
    public ClosingPeriod mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int id = rs.getInt("id");
        int companyid = rs.getInt("companyid");        
        String period = rs.getString("period");
        Date opendate = rs.getDate("opendate");
        Date closedate = rs.getDate("closedate");
        Boolean closed = rs.getBoolean("closed");
        
        return new ClosingPeriod(id, companyid, period, opendate, closedate, closed);
    }
}