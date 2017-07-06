package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.SpringMVC.model.UserLogin;
 
public class UserLoginMapper implements RowMapper<UserLogin> {
	
	@Override
	public UserLogin mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int userid = rs.getInt("userid");
        String username = rs.getString("username");
        String password = rs.getString("password");
        String role = rs.getString("role");
        int teamid = rs.getInt("teamid");
        String teamname = rs.getString("teamname");
        int branchid = rs.getInt("branchid");
        String branchname = rs.getString("branchname");
        int companyid = rs.getInt("companyid");
        String companyname = rs.getString("companyname");
        String mobile = rs.getString("mobile");
        String email = rs.getString("email");
        String imgurl = rs.getString("imgurl");
        String imgthumburl = rs.getString("imgthumburl");

        return new UserLogin(userid, username, password, role, 
        		teamid, teamname, branchid, branchname, 
        		companyid, companyname, mobile, email,
        		imgurl, imgthumburl);
    }
 
}