package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Branch;
import org.springframework.jdbc.core.RowMapper;
 
public class BranchMapper implements RowMapper<Branch> {
 
    @Override
    public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        int branchid = rs.getInt("branchid");
        String branchname = rs.getString("branchname");
        int companyid = rs.getInt("companyid");
        String regno = rs.getString("regno");
        int maid = rs.getInt("maid");
        String maname = rs.getString("maname");
        Address address = new Address();
        address.setcountry(rs.getString("country"));
        address.setzipcode(rs.getString("zipcode"));
        address.setstate(rs.getString("state"));
        address.setcity(rs.getString("city"));
        address.setstreet(rs.getString("street"));
        String telephone = rs.getString("telephone");
        String fax = rs.getString("fax");
        String email = rs.getString("email");
        String website = rs.getString("website");
        
        return new Branch(branchid, branchname, companyid, regno, maid, maname, 
        		address, telephone, fax, email, website);
    }
 
}