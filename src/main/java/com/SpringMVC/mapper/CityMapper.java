package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.City;
import org.springframework.jdbc.core.RowMapper;
 
public class CityMapper implements RowMapper<City> {
 
    @Override
    public City mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int cityid = rs.getInt("cityid");
        int stateid = rs.getInt("stateid");
        int countryid = rs.getInt("countryid");
        String countryname = rs.getString("countryname");
        String statename = rs.getString("statename");
        String cityname = rs.getString("cityname");
        
        return new City(cityid, stateid, countryid, countryname, statename, cityname);
    }
}