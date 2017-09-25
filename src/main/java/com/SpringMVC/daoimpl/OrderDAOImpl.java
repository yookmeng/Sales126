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
    
    public Order get(int orderid) {
        String sql = "SELECT ord.orderid, ord.orderdate, ord.projectid, proj.projectname, "
        		+ "ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "ord.quantity, ord.price, ord.amount, proj.hwdiscount, proj.swdiscount "
        		+ "FROM tblOrder ord "
        		+ "LEFT JOIN tblProject proj ON proj.projectid = ord.projectid "
        		+ "LEFT JOIN tblProduct prod ON prod.productid = ord.productid "        		
        		+ "LEFT JOIN tblCodeMaster code ON code.codetype = 'PRODUCT' AND code.codeid = prod.prodtype "        		
        		+ "WHERE ord.orderid = " + orderid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Order>() {
	 
	        @Override
	        public Order extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Order order = new Order();
	                order.setorderid(rs.getInt("orderid"));
	                order.setorderdate(rs.getDate("orderdate"));
	                order.setprojectid(rs.getInt("projectid"));
	                order.setprojectname(rs.getString("projectname"));
	                order.setproductid(rs.getInt("productid"));
	                order.setproductname(rs.getString("productname"));
	                order.setprodtype(rs.getString("prodtype"));
	                order.setprodtypename(rs.getString("prodtypename"));
	                order.setquantity(rs.getInt("quantity"));
	                order.setprice(rs.getFloat("price"));
	                order.setamount(rs.getFloat("amount"));
	                order.sethwdiscount(rs.getFloat("hwdiscount"));
	                order.setswdiscount(rs.getFloat("swdiscount"));
	                return order;
	            }	 
	            return null;
	        }
        });
    }

    public List<Order> list(int userid) {
        String sql = "SELECT ord.orderid, ord.orderdate, ord.projectid, proj.projectname, "
        		+ "ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "ord.quantity, ord.price, ord.amount, proj.hwdiscount, proj.swdiscount "
        		+ "FROM tblOrder ord "
        		+ "LEFT JOIN tblProject proj ON proj.projectid = ord.projectid "
        		+ "LEFT JOIN tblProduct prod ON prod.productid = ord.productid "        		
        		+ "LEFT JOIN tblCodeMaster code ON code.codetype = 'PRODUCT' AND code.codeid = prod.prodtype "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.userid = " + userid + " "
        		+ "ORDER BY proj.projectname";
        OrderMapper mapper = new OrderMapper();
        List<Order> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<Order> listByTeam(int teamid) {
        String sql = "SELECT ord.orderid, ord.orderdate, ord.projectid, proj.projectname, "
        		+ "ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "ord.quantity, ord.price, ord.amount, proj.hwdiscount, proj.swdiscount "
        		+ "FROM tblOrder ord "
        		+ "LEFT JOIN tblProject proj ON proj.projectid = ord.projectid "
        		+ "LEFT JOIN tblProduct prod ON prod.productid = ord.productid "        		
        		+ "LEFT JOIN tblCodeMaster code ON code.codetype = 'PRODUCT' AND code.codeid = prod.prodtype "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.teamid = " + teamid + " "
        		+ "ORDER BY proj.projectname";
        OrderMapper mapper = new OrderMapper();
        List<Order> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Order> listByBranch(int branchid) {
        String sql = "SELECT ord.orderid, ord.orderdate, ord.projectid, proj.projectname, "
        		+ "ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "ord.quantity, ord.price, ord.amount, proj.hwdiscount, proj.swdiscount "
        		+ "FROM tblOrder ord "
        		+ "LEFT JOIN tblProject proj ON proj.projectid = ord.projectid "
        		+ "LEFT JOIN tblProduct prod ON prod.productid = ord.productid "        		
        		+ "LEFT JOIN tblCodeMaster code ON code.codetype = 'PRODUCT' AND code.codeid = prod.prodtype "        		
        		+ "LEFT JOIN tblUsr user ON usr.userid = proj.userid "
        		+ "WHERE usr.branchid = " + branchid + " "
        		+ "ORDER BY proj.projectname";
        OrderMapper mapper = new OrderMapper();
        List<Order> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Order> listByCompany(int companyid) {
        String sql = "SELECT ord.orderid, ord.orderdate, ord.projectid, proj.projectname, "
        		+ "ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "ord.quantity, ord.price, ord.amount, proj.hwdiscount, proj.swdiscount "
        		+ "FROM tblOrder ord "
        		+ "LEFT JOIN tblProject proj ON proj.projectid = ord.projectid "
        		+ "LEFT JOIN tblProduct prod ON prod.productid = ord.productid "        		
        		+ "LEFT JOIN tblCodeMaster code ON code.codetype = 'PRODUCT' AND code.codeid = prod.prodtype "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.companyid = " + companyid + " "
        		+ "ORDER BY proj.projectname";
        OrderMapper mapper = new OrderMapper();
        List<Order> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public boolean isExist(Order order) {
        return get(order.getorderid())!=null;
    }
}