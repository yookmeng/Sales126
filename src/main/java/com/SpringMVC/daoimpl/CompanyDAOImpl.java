package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Company;
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.mapper.CompanyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class CompanyDAOImpl extends JdbcDaoSupport implements CompanyDAO {
 
    @Autowired
    public CompanyDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Company company) {
    	Address address = company.getaddress();
        String sql = "INSERT INTO tblCompany "
        		+ "(companyname, regno, mdid, "
        		+ "address.country, address.zipcode, address.state, address.city, address.street, "
        		+ "telephone, fax, email, website, said) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		company.getcompanyname(), company.getregno(), company.getmdid(),
        		address.getcountry(), address.getzipcode(), address.getstate(), address.getcity(), address.getstreet(),
        		company.gettelephone(), company.getfax(), company.getemail(), company.getwebsite(), company.getsaid());
    }
    
    public void update(Company company) {
        Address address = company.getaddress();
        String sql = "UPDATE tblCompany SET "
	    		+ "companyname=?, regno=?, mdid=?, "
	    		+ "address.country=?, address.zipcode=?, address.state=?, address.city=?, address.street=?, "
	    		+ "telephone=?, fax=?, email=?, website=?, said=? "
	    		+ "WHERE companyid=?";
        this.getJdbcTemplate().update(sql, 
	    		company.getcompanyname(), company.getregno(), company.getmdid(),
	    		address.getcountry(), address.getzipcode(), address.getstate(), address.getcity(), address.getstreet(),
	    		company.gettelephone(), company.getfax(), company.getemail(), company.getwebsite(), 
	    		company.getsaid(), company.getcompanyid());
    }
    
    public void delete(Company company) {
        String sql = "DELETE FROM tblCompany WHERE companyid=?";
        this.getJdbcTemplate().update(sql, company.getcompanyid());
    }
    
    public List<Company> list() {
	    String sql = "SELECT c.companyid AS companyid, "
	    		+ "companyname AS companyname, "
	    		+ "regno AS regno, "
	    		+ "c.mdid AS mdid, "
	    		+ "(address).country AS country, "
	    		+ "(address).zipcode AS zipcode, "
	    		+ "(address).state AS state, "
	    		+ "(address).city AS city, "
	    		+ "(address).street AS street, "
	    		+ "telephone AS telephone, "
	    		+ "fax AS fax, "
	    		+ "c.email AS email, "
	    		+ "website AS website, "
	    		+ "c.said AS said, "
	    		+ "md.username AS mdname, "
	    		+ "sa.username AS saname "
	    		+ "FROM tblCompany c "
	    		+ "LEFT JOIN tblUser md on c.mdid = md.userid "
	    		+ "LEFT JOIN tblUser sa on c.said = sa.userid ";
        CompanyMapper mapper = new CompanyMapper();
        List<Company> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }                          

    public List<String> companyList() {
        String sql = "SELECT companyname FROM tblCompany";
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Company get(int companyid) {
	    String sql = "SELECT c.companyid AS companyid, "
	    		+ "companyname AS companyname, "
	    		+ "regno AS regno, "
	    		+ "c.mdid AS mdid, "
	    		+ "(address).country AS country, "
	    		+ "(address).zipcode AS zipcode, "
	    		+ "(address).state AS state, "
	    		+ "(address).city AS city, "
	    		+ "(address).street AS street, "
	    		+ "telephone AS telephone, "
	    		+ "fax AS fax, "
	    		+ "c.email AS email, "
	    		+ "website AS website, "
	    		+ "c.said AS said, "
	    		+ "md.username AS mdname, "
	    		+ "sa.username AS saname "
	    		+ "FROM tblCompany c "
	    		+ "LEFT JOIN tblUser md on c.mdid = md.userid "
	    		+ "LEFT JOIN tblUser sa on c.said = sa.userid "
	    		+ "WHERE c.companyid=" + companyid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Company>() {
	 
	        @Override
	        public Company extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Company company = new Company();
	                company.setcompanyid(rs.getInt("companyid"));
	                company.setcompanyname(rs.getString("companyname"));
	                company.setregno(rs.getString("regno"));
	                company.setmdid(rs.getInt("mdid"));
	                company.setmdname(rs.getString("mdname"));
	                Address address = new Address();
	                address.setcountry(rs.getString("country"));
	                address.setzipcode(rs.getString("zipcode"));
	                address.setstate(rs.getString("state"));
	                address.setcity(rs.getString("city"));
	                address.setstreet(rs.getString("street"));
	                company.setaddress(address);
	                company.settelephone(rs.getString("telephone"));
	                company.setfax(rs.getString("fax"));
	                company.setemail(rs.getString("email"));
	                company.setwebsite(rs.getString("website"));
	                company.setsaid(rs.getInt("said"));
	                company.setsaname(rs.getString("saname"));
	                return company;
	            }	 
	            return null;
	        }
        });
    }    
}