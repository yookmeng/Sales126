package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.CodeMaster;
import org.springframework.jdbc.core.RowMapper;
 
public class CodeMasterMapper implements RowMapper<CodeMaster> {
 
    @Override
    public CodeMaster mapRow(ResultSet rs, int rowNum) throws SQLException { 
        String codetype = rs.getString("codetype");
        String codeid = rs.getString("codeid");
        String codename = rs.getString("codename");
        
        return new CodeMaster(codetype, codeid, codename);
    }
}