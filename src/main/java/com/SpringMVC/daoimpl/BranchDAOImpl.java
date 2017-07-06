	package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Branch;
import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.mapper.BranchMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class BranchDAOImpl extends JdbcDaoSupport implements BranchDAO { 
    @Autowired
    public BranchDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Branch branch) {
    	Address address = branch.getaddress();

        String sql = "INSERT INTO tblBranch "
        		+ "(companyid, branchname, regno, maid, "
        		+ "address.country, address.zipcode, address.state, address.city, address.street, "
        		+ "telephone, fax, email, website) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		branch.getcompanyid(), branch.getbranchname(), branch.getregno(), branch.getmaid(),
        		address.getcountry(), address.getzipcode(), address.getstate(),
        		address.getcity(), address.getstreet(), branch.gettelephone(),
        		branch.getfax(), branch.getemail(), branch.getwebsite());
    }
    
    public void update(Branch branch) {
    	Address address = branch.getaddress();
    	String sql = "UPDATE tblBranch SET "
	    		+ "branchname=?, regno=?, maid=?, "
	    		+ "address.country=?, address.zipcode=?, address.state=?, address.city=?, address.street=?, "
	    		+ "telephone=?, fax=?, email=?, website=? "
	    		+ "WHERE branchid=?";
        this.getJdbcTemplate().update(sql, 
	    		branch.getbranchname(), branch.getregno(), branch.getmaid(),
	    		address.getcountry(), address.getzipcode(), address.getstate(), address.getcity(), address.getstreet(), 
	    		branch.gettelephone(), branch.getfax(), branch.getemail(), branch.getwebsite(), branch.getbranchid());
    }
    
    public void delete(int branchid) {
        String sql = "DELETE FROM tblBranch WHERE branchid=?";
        this.getJdbcTemplate().update(sql, branchid);
    }
    
    public List<Branch> list(int companyid) {
	    String sql = "SELECT b.branchid AS branchid, "
	    		+ "branchname AS branchname, "
	    		+ "b.companyid AS companyid, "
	    		+ "regno AS regno, "
	    		+ "b.maid AS maid, "
	    		+ "ma.username AS maname, "
	    		+ "(address).country AS country, "
	    		+ "(address).zipcode AS zipcode, "
	    		+ "(address).state AS state, "
	    		+ "(address).city AS city, "
	    		+ "(address).street AS street, "
	    		+ "telephone AS telephone, "
	    		+ "fax AS fax, "
	    		+ "b.email AS email, "
	    		+ "website AS website "
	    		+ "FROM tblBranch b "
	    		+ "LEFT JOIN tblUser ma on b.maid = ma.userid "
        		+ "WHERE b.companyid=" + companyid;
        BranchMapper mapper = new BranchMapper();
        List<Branch> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<String> branchList(int companyid) {
        String sql = "SELECT branchname FROM tblBranch WHERE companyid=" + companyid;
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Branch get(int branchid) {
	    String sql = "SELECT b.branchid AS branchid, "
	    		+ "branchname AS branchname, "
	    		+ "b.companyid AS companyid, "
	    		+ "regno AS regno, "
	    		+ "b.maid AS maid, "
	    		+ "ma.username AS maname, "
	    		+ "(address).country AS country, "
	    		+ "(address).zipcode AS zipcode, "
	    		+ "(address).state AS state, "
	    		+ "(address).city AS city, "
	    		+ "(address).street AS street, "
	    		+ "telephone AS telephone, "
	    		+ "fax AS fax, "
	    		+ "b.email AS email, "
	    		+ "website AS website "
	    		+ "FROM tblBranch b "
	    		+ "LEFT JOIN tblUser ma on b.maid = ma.userid "
	    		+ "WHERE b.branchid=" + branchid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Branch>() {
	 
	        @Override
	        public Branch extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Branch branch = new Branch();
	                branch.setbranchid(rs.getInt("branchid"));
	                branch.setbranchname(rs.getString("branchname"));
	                branch.setcompanyid(rs.getInt("companyid"));
	                branch.setregno(rs.getString("regno"));
	                branch.setmaid(rs.getInt("maid"));
	                branch.setmaname(rs.getString("maname"));
	                Address address = new Address();
	                address.setcountry(rs.getString("country"));
	                address.setzipcode(rs.getString("zipcode"));
	                address.setstate(rs.getString("state"));
	                address.setcity(rs.getString("city"));
	                address.setstreet(rs.getString("street"));
	                branch.setaddress(address);
	                branch.settelephone(rs.getString("telephone"));
	                branch.setfax(rs.getString("fax"));
	                branch.setemail(rs.getString("email"));
	                branch.setwebsite(rs.getString("website"));
	                return branch;
	            }	 
	            return null;
	        }
        });
    }
    
    public Branch getByName(String branchname) {
	    String sql = "SELECT b.branchid AS branchid, "
	    		+ "branchname AS branchname, "
	    		+ "b.companyid AS companyid, "
	    		+ "regno AS regno, "
	    		+ "b.maid AS maid, "
	    		+ "ma.username AS maname, "
	    		+ "(address).country AS country, "
	    		+ "(address).zipcode AS zipcode, "
	    		+ "(address).state AS state, "
	    		+ "(address).city AS city, "
	    		+ "(address).street AS street, "
	    		+ "telephone AS telephone, "
	    		+ "fax AS fax, "
	    		+ "b.email AS email, "
	    		+ "website AS website "
	    		+ "FROM tblBranch b "
	    		+ "LEFT JOIN tblUser ma on b.maid = ma.userid "	    		
	    		+ "WHERE branchname='" + branchname + "'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Branch>() {
	 
	        @Override
	        public Branch extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Branch branch = new Branch();
	                branch.setbranchid(rs.getInt("branchid"));
	                branch.setbranchname(rs.getString("branchname"));
	                branch.setcompanyid(rs.getInt("companyid"));
	                branch.setregno(rs.getString("regno"));
	                branch.setmaid(rs.getInt("maid"));
	                branch.setmaname(rs.getString("maname"));
	                Address address = new Address();
	                address.setcountry(rs.getString("country"));
	                address.setzipcode(rs.getString("zipcode"));
	                address.setstate(rs.getString("state"));
	                address.setcity(rs.getString("city"));
	                address.setstreet(rs.getString("street"));
	                branch.setaddress(address);
	                branch.settelephone(rs.getString("telephone"));
	                branch.setfax(rs.getString("fax"));
	                branch.setemail(rs.getString("email"));
	                branch.setwebsite(rs.getString("website"));
	                return branch;
	            }	 
	            return null;
	        }
        });
    }

    public Branch getByMA(int maid) {
	    String sql = "SELECT b.branchid AS branchid, "
	    		+ "branchname AS branchname, "
	    		+ "b.companyid AS companyid, "
	    		+ "regno AS regno, "
	    		+ "b.maid AS maid, "
	    		+ "ma.username AS maname, "
	    		+ "(address).country AS country, "
	    		+ "(address).zipcode AS zipcode, "
	    		+ "(address).state AS state, "
	    		+ "(address).city AS city, "
	    		+ "(address).street AS street, "
	    		+ "telephone AS telephone, "
	    		+ "fax AS fax, "
	    		+ "b.email AS email, "
	    		+ "website AS website "
	    		+ "FROM tblBranch b "
	    		+ "LEFT JOIN tblUser ma on b.maid = ma.userid "
	    		+ "WHERE b.maid=" + maid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Branch>() {
	 
	        @Override
	        public Branch extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Branch branch = new Branch();
	                branch.setbranchid(rs.getInt("branchid"));
	                branch.setbranchname(rs.getString("branchname"));
	                branch.setcompanyid(rs.getInt("companyid"));
	                branch.setregno(rs.getString("regno"));
	                branch.setmaid(rs.getInt("maid"));
	                branch.setmaname(rs.getString("maname"));
	                Address address = new Address();
	                address.setcountry(rs.getString("country"));
	                address.setzipcode(rs.getString("zipcode"));
	                address.setstate(rs.getString("state"));
	                address.setcity(rs.getString("city"));
	                address.setstreet(rs.getString("street"));
	                branch.setaddress(address);
	                branch.settelephone(rs.getString("telephone"));
	                branch.setfax(rs.getString("fax"));
	                branch.setemail(rs.getString("email"));
	                branch.setwebsite(rs.getString("website"));
	                return branch;
	            }	 
	            return null;
	        }
        });
    }
}