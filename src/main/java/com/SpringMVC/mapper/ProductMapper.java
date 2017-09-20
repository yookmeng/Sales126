package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Product;
import org.springframework.jdbc.core.RowMapper;
 
public class ProductMapper implements RowMapper<Product> {
 
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int productid = rs.getInt("productid");
        String productname = rs.getString("productname");
        String prodtype = rs.getString("prodtype");
        String prodtypename = rs.getString("prodtypename");
        int companyid = rs.getInt("companyid");
        float price = rs.getFloat("price");
        String desc01 = rs.getString("desc01");
        String desc02 = rs.getString("desc02");
        String desc03 = rs.getString("desc03");
        String desc04 = rs.getString("desc04");
        String desc05 = rs.getString("desc05");
        String desc06 = rs.getString("desc06");
        String desc07 = rs.getString("desc07");
        String desc08 = rs.getString("desc08");
        String desc09 = rs.getString("desc09");
        String desc10 = rs.getString("desc10");

        return new Product(productid, productname, prodtype, prodtypename, companyid,
        		price, desc01, desc02, desc03, desc04, desc05,
        		desc06, desc07, desc08, desc09, desc10);        
    }
}