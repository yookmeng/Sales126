package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.ExcelDetail;
import org.springframework.jdbc.core.RowMapper;
 
public class ExcelDetailMapper implements RowMapper<ExcelDetail> {
 
    @Override
    public ExcelDetail mapRow(ResultSet rs, int rowNum) throws SQLException { 
        String period = rs.getString("period");
        int branchid = rs.getInt("branchid");
        String branchname = rs.getString("branchname");        
        int teamid = rs.getInt("teamid");
        String teamname = rs.getString("teamname");        
        int userid = rs.getInt("userid");
        String username = rs.getString("username");        
        int prospectid = rs.getInt("prospectid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String mobile = rs.getString("mobile");
        String email = rs.getString("email");
        String brandname = rs.getString("brandname");
        String modelname = rs.getString("modelname");
        String demo = rs.getString("demo");
        String testdrive = rs.getString("testdrive");
        String quotation = rs.getString("quotation");
        String status = rs.getString("status");
        String statusname = rs.getString("statusname");
        String closed = rs.getString("closed");
        String lost = rs.getString("lost");
        String diary = rs.getString("diary");
        String remark = rs.getString("remark");
        
        return new ExcelDetail(period, branchid, branchname, teamid, teamname, 
        		userid, username, prospectid, firstname, lastname, mobile, email,
        		brandname, modelname, demo, testdrive, quotation, 
        		status, statusname, closed, lost, diary, remark);
    }
}