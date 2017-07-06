package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.BranchTarget;
import org.springframework.jdbc.core.RowMapper;
 
public class BranchTargetMapper implements RowMapper<BranchTarget> {
 
    @Override
    public BranchTarget mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int targetid = rs.getInt("targetid");
        int branchid = rs.getInt("branchid");
        String branchname = rs.getString("branchname");
        String period = rs.getString("period");
        int companytargetid = rs.getInt("companytargetid");
        int prospect = rs.getInt("prospect");
        int testdrive = rs.getInt("testdrive");
        int closed = rs.getInt("closed");
        
        return new BranchTarget(targetid, branchid, branchname, period, 
        		companytargetid, prospect, testdrive, closed);
    }
}