package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.SpringMVC.model.Closed;
import org.springframework.jdbc.core.RowMapper;
 
public class ClosedMapper implements RowMapper<Closed> {
 
    @Override
    public Closed mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int closedid = rs.getInt("closedid");
        Date closedate = rs.getDate("closedate");
        int prospectid = rs.getInt("prospectid");
        int activityid = rs.getInt("activityid");
        float downpayment = rs.getFloat("downpayment");
        String remark = rs.getString("remark");
        
        return new Closed(closedid, closedate, prospectid, activityid, downpayment, remark);
    }
}