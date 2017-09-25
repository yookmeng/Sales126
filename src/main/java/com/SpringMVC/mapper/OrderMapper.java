package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SpringMVC.model.Order;
import org.springframework.jdbc.core.RowMapper;
 
public class OrderMapper implements RowMapper<Order> {
 
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int orderid = rs.getInt("orderid");
        Date orderdate = rs.getDate("orderdate");
        int projectid = rs.getInt("projectid");
        String projectname = rs.getString("projectname");
        int productid = rs.getInt("productid");
        String productname = rs.getString("productname");
        String prodtype = rs.getString("prodtype");
        String prodtypename = rs.getString("prodtypename");
        int quantity = rs.getInt("quantity");
        float price = rs.getFloat("price");
        float amount = rs.getFloat("amount");
        float hwdiscount = rs.getFloat("hwdiscount");
        float swdiscount = rs.getFloat("swdiscount");
        
        return new Order(orderid, orderdate, projectid, projectname, 
        		productid, productname, prodtype, prodtypename, 
        		quantity, price, amount, hwdiscount, swdiscount);
    }
}