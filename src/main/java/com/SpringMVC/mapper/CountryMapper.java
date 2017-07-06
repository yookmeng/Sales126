package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Country;
import org.springframework.jdbc.core.RowMapper;
 
public class CountryMapper implements RowMapper<Country> {
 
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int countryid = rs.getInt("countryid");
        String countrycode = rs.getString("countrycode");
        String countryname = rs.getString("countryname");
        String iddcode = rs.getString("iddcode");
        
        return new Country(countryid, countrycode, countryname, iddcode);
    }
}