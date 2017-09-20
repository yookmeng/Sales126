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
        String productdesc1 = rs.getString("productdesc1");
        String productdesc2 = rs.getString("productdesc2");
        String productdesc3 = rs.getString("productdesc3");

        return new Product(productid, productname, prodtype, prodtypename, companyid,
        		price, productdesc1, productdesc2, productdesc3);        
    }
}