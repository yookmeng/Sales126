	package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Order;
import com.SpringMVC.dao.OrderDAO;
import com.SpringMVC.mapper.OrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class OrderDAOImpl extends JdbcDaoSupport implements OrderDAO { 
    @Autowired
    public OrderDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Order order) {
        // insert
        String sql = "INSERT INTO tblOrder (projectid, productid, quantity, price, amount) "
        		+ "VALUES (?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		order.getprojectid(), order.getproductid(), order.getquantity(), 
        		order.getprice(), order.getamount());
    }
    
    public void update(Order order) {
        // update
        String sql = "UPDATE tblOrder SET productid=?, quantity=?, price=?, amount=? "
        		+ "WHERE orderid=?";
        this.getJdbcTemplate().update(sql, 
        		order.getproductid(), order.getquantity(), order.getprice(), order.getamount(), 
        		order.getorderid());
    }

    public void delete(int orderid) {
        String sql = "DELETE FROM tblOrder WHERE orderid=?";
        this.getJdbcTemplate().update(sql, orderid);
    }
    
    public List<Order> getAll(int projectid) {
        String sql = "SELECT ord.orderid, ord.projectid, ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "ord.quantity, ord.price, ord.amount "
        		+ "FROM tblOrder ord "
        		+ "LEFT JOIN tblProduct prod ON prod.productid = ord.productid "        		
        		+ "LEFT JOIN tblCodeMaster code ON code.codetype = 'PRODTYPE' AND code.codeid = prod.prodtype "        		
        		+ "WHERE ord.projectid = " + projectid;
        OrderMapper mapper = new OrderMapper();
        List<Order> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Order get(int orderid) {
        String sql = "SELECT ord.orderid, ord.projectid, ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "ord.quantity, ord.price, ord.amount "
        		+ "FROM tblOrder ord "
        		+ "LEFT JOIN tblProduct prod ON prod.productid = ord.productid "        		
        		+ "LEFT JOIN tblCodeMaster code ON code.codetype = 'PRODTYPE' AND code.codeid = prod.prodtype "        		
        		+ "WHERE ord.orderid = " + orderid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Order>() {
	 
	        @Override
	        public Order extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Order order = new Order();
	                order.setorderid(rs.getInt("orderid"));
	                order.setprojectid(rs.getInt("projectid"));
	                order.setproductid(rs.getInt("productid"));
	                order.setproductname(rs.getString("productname"));
	                order.setprodtype(rs.getInt("prodtype"));
	                order.setprodtypename(rs.getString("prodtypename"));
	                order.setquantity(rs.getInt("quantity"));
	                order.setprice(rs.getFloat("price"));
	                order.setamount(rs.getFloat("amount"));
	                return order;
	            }	 
	            return null;
	        }
        });
    }

   public boolean isExist(Order order) {
        return get(order.getorderid())!=null;
    }
}