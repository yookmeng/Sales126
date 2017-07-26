package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.Order;
import org.springframework.jdbc.core.RowMapper;
 
public class OrderMapper implements RowMapper<Order> {
 
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int orderid = rs.getInt("orderid");
        int projectid = rs.getInt("projectid");
        int productid = rs.getInt("productid");
        String productname = rs.getString("productname");
        int prodtype = rs.getInt("prodtype");
        String prodtypename = rs.getString("prodtypename");
        int quantity = rs.getInt("quantity");
        float price = rs.getFloat("price");
        float amount = rs.getFloat("amount");
        
        return new Order(orderid, projectid, productid, productname, 
        		prodtype, prodtypename, quantity, price, amount);
    }
}