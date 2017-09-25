package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Project;
import org.springframework.jdbc.core.RowMapper;
 
public class ProjectMapper implements RowMapper<Project> {
 
    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int projectid = rs.getInt("projectid");
        String projectname = rs.getString("projectname");
        Address address = new Address();
        address.setcountry(rs.getString("country"));
        address.setzipcode(rs.getString("zipcode"));
        address.setstate(rs.getString("state"));
        address.setcity(rs.getString("city"));
        address.setstreet(rs.getString("street"));
        int userid = rs.getInt("userid");
        String username = rs.getString("username");
        String name = rs.getString("name");
        String mobile = rs.getString("mobile");
        String email = rs.getString("email");
        String titleid = rs.getString("titleid");
        String titlename = rs.getString("titlename");
        String propertyid = rs.getString("propertyid");
        String propertyname = rs.getString("propertyname");
        int units = rs.getInt("units");
        boolean smsflag = rs.getBoolean("smsflag");
        Date datecreated = rs.getDate("datecreated");
        String forecastperiod = rs.getString("forecastperiod");
        float hwdiscount = rs.getFloat("hwdiscount");
        float swdiscount = rs.getFloat("swdiscount");
        String status = rs.getString("status");
        String statusname = rs.getString("statusname");
        
        return new Project(projectid, projectname, address, userid, username, name, mobile, email, 
        		titleid, titlename, propertyid, propertyname, units, 
        		smsflag, datecreated, forecastperiod, hwdiscount, swdiscount,
        		status, statusname);
    } 
}