package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Brand;
import org.springframework.jdbc.core.RowMapper;
 
public class BrandMapper implements RowMapper<Brand> {
 
    @Override
    public Brand mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int brandid = rs.getInt("brandid");
        String brandname = rs.getString("brandname");
        int companyid = rs.getInt("companyid");
        boolean sellingbrand = rs.getBoolean("sellingbrand");
        
        return new Brand(brandid, brandname, companyid, sellingbrand);
    }
}