package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Contact;
import org.springframework.jdbc.core.RowMapper;
 
public class ContactMapper implements RowMapper<Contact> {
 
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int contactid = rs.getInt("contactid");
        int userid = rs.getInt("userid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String mobile = rs.getString("mobile");
        String home = rs.getString("home");
        String work = rs.getString("work");
        String email = rs.getString("email");
        Date birthday = rs.getDate("birthday");
        Address address = new Address();
        address.setcountry(rs.getString("country"));
        address.setzipcode(rs.getString("zipcode"));
        address.setstate(rs.getString("state"));
        address.setcity(rs.getString("city"));
        address.setstreet(rs.getString("street"));
        String company = rs.getString("company");
        String title = rs.getString("title");
        String note = rs.getString("note");
        String website = rs.getString("website");
        
        return new Contact(contactid, userid, firstname, lastname, 
        		mobile, home, work, email, birthday, 
        		address, company, title, note, website);
    } 
}