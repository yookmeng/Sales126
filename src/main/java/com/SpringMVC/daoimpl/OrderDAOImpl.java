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
        String sql = "INSERT INTO tblOrder (orderdate, projectid, productid, quantity, price, amount) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		order.getorderdate(), order.getprojectid(), order.getproductid(), 
        		order.getquantity(), order.getprice(), order.getamount());
    }
    
    public void update(Order order) {
        // update
        String sql = "UPDATE tblOrder SET orderdate=?, productid=?, quantity=?, price=?, amount=? "
        		+ "WHERE orderid=?";
        this.getJdbcTemplate().update(sql, 
        		order.getorderdate(), order.getproductid(), order.getquantity(), 
        		order.getprice(), order.getamount(), order.getorderid());
    }

    public void delete(int orderid) {
        String sql = "DELETE FROM tblOrder WHERE orderid=?";
        this.getJdbcTemplate().update(sql, orderid);
    }
    
    public Order get(int orderid) {
        String sql = "SELECT ord.orderid, ord.orderdate, ord.projectid, proj.projectname, "
        		+ "ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "prod.desc01, prod.desc02, prod.desc03, prod.desc04, prod.desc05, "
        		+ "prod.desc06, prod.desc07, prod.desc08, prod.desc09, prod.desc10, "        		
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
	                order.setdesc01(rs.getString("desc01"));
	                order.setdesc02(rs.getString("desc02"));
	                order.setdesc03(rs.getString("desc03"));
	                order.setdesc04(rs.getString("desc04"));
	                order.setdesc05(rs.getString("desc05"));
	                order.setdesc06(rs.getString("desc06"));
	                order.setdesc07(rs.getString("desc07"));
	                order.setdesc08(rs.getString("desc08"));
	                order.setdesc09(rs.getString("desc09"));
	                order.setdesc10(rs.getString("desc10"));
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

    public List<Order> listByProject(int projectid) {
        String sql = "SELECT ord.orderid, ord.orderdate, ord.projectid, proj.projectname, "
        		+ "ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "prod.desc01, prod.desc02, prod.desc03, prod.desc04, prod.desc05, "
        		+ "prod.desc06, prod.desc07, prod.desc08, prod.desc09, prod.desc10, "        		
        		+ "ord.quantity, ord.price, ord.amount, proj.hwdiscount, proj.swdiscount "
        		+ "FROM tblOrder ord "
        		+ "LEFT JOIN tblProject proj ON proj.projectid = ord.projectid "
        		+ "LEFT JOIN tblProduct prod ON prod.productid = ord.productid "        		
        		+ "LEFT JOIN tblCodeMaster code ON code.codetype = 'PRODUCT' AND code.codeid = prod.prodtype "        		
        		+ "WHERE ord.projectid = " + projectid + " "
        		+ "ORDER BY prod.prodtype";
        OrderMapper mapper = new OrderMapper();
        List<Order> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<Order> list(int userid) {
        String sql = "SELECT ord.orderid, ord.orderdate, ord.projectid, proj.projectname, "
        		+ "ord.productid, prod.productname, "
        		+ "prod.prodtype, code.codename AS prodtypename, "
        		+ "prod.desc01, prod.desc02, prod.desc03, prod.desc04, prod.desc05, "
        		+ "prod.desc06, prod.desc07, prod.desc08, prod.desc09, prod.desc10, "        		
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
        		+ "prod.desc01, prod.desc02, prod.desc03, prod.desc04, prod.desc05, "
        		+ "prod.desc06, prod.desc07, prod.desc08, prod.desc09, prod.desc10, "        		
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
        		+ "prod.desc01, prod.desc02, prod.desc03, prod.desc04, prod.desc05, "
        		+ "prod.desc06, prod.desc07, prod.desc08, prod.desc09, prod.desc10, "        		
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
        		+ "prod.desc01, prod.desc02, prod.desc03, prod.desc04, prod.desc05, "
        		+ "prod.desc06, prod.desc07, prod.desc08, prod.desc09, prod.desc10, "        		
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