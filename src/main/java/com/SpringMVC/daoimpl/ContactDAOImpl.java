package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Contact;
import com.SpringMVC.dao.ContactDAO;
import com.SpringMVC.mapper.ContactMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository 
public class ContactDAOImpl extends JdbcDaoSupport implements ContactDAO {
	
	@Autowired
    public ContactDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

	public void save(Contact contact) {
		Address address = contact.getaddress();
		
        String sql = "INSERT INTO tblContact "
        		+ "(userid, firstname, lastname, mobile, home, work, email, birthday, "
        		+ "address.country, address.zipcode, address.state, address.city, address.street, "
        		+ "company, title, note, website) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		contact.getuserid(), contact.getfirstname(), contact.getlastname(), 
        		contact.getmobile(), contact.gethome(), contact.getwork(), 
        		contact.getemail(), contact.getbirthday(), 
        		address.getcountry(), address.getzipcode(), address.getstate(), 
        		address.getcity(), address.getstreet(), contact.getcompany(), 
        		contact.gettitle(), contact.getnote(), contact.getwebsite());
	}
    
    public void update(Contact contact) {
		Address address = contact.getaddress();

        String sql = "UPDATE tblContact SET firstname=?, lastname=?, mobile=?, "
        		+ "home=?, work=?, email=?, birthday=?, address.country=?, "
        		+ "address.zipcode=?, address.state=?, address.city=?, address.street=?, "
        		+ "company=?, title=?, note=?, website=? WHERE contactid=?";
        this.getJdbcTemplate().update(sql, contact.getfirstname(), contact.getlastname(), 
        		contact.getmobile(), contact.gethome(), contact.getwork(), 
        		contact.getemail(), contact.getbirthday(), 
        		address.getcountry(), address.getzipcode(), address.getstate(), 
        		address.getcity(), address.getstreet(), contact.getcompany(), 
        		contact.gettitle(), contact.getnote(), contact.getwebsite(),
        		contact.getcontactid());
    }
 
    public void delete(int contactid) {
        String sql = "DELETE FROM tblContact WHERE contactid=?";
        this.getJdbcTemplate().update(sql, contactid);
    }
    
	public List<Contact> list(int userid) {
        String sql = "SELECT contactid, userid, firstname, lastname, "
        		+ "mobile, home, work, email, birthday, (address).country AS country, "
        		+ "(address).zipcode AS zipcode, (address).state AS state, (address).city AS city, "
        		+ "(address).street AS street, company, title, note, website "
        		+ "FROM tblContact WHERE userid = " + userid;
        ContactMapper mapper = new ContactMapper();
        List<Contact> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

	public Contact get(int contactid) {
        String sql = "SELECT contactid, userid, firstname, lastname, "
        		+ "mobile, home, work, email, birthday, (address).country AS country, "
        		+ "(address).zipcode AS zipcode, (address).state AS state, (address).city AS city, "
        		+ "(address).street AS street, company, title, note, website "
        		+ "FROM tblContact WHERE contactid=" + contactid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Contact>() {
	 
	        @Override
	        public Contact extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Contact contact = new Contact();
	            	contact.setcontactid(rs.getInt("contactid"));
	            	contact.setuserid(rs.getInt("userid"));
	            	contact.setfirstname(rs.getString("firstname"));
	            	contact.setlastname(rs.getString("lastname"));
	            	contact.setmobile(rs.getString("mobile"));
	            	contact.sethome(rs.getString("home"));
	            	contact.setwork(rs.getString("work"));
	            	contact.setemail(rs.getString("email"));
	            	contact.setbirthday(rs.getDate("birthday"));
	                Address address = new Address();
	                address.setcountry(rs.getString("country"));
	                address.setzipcode(rs.getString("zipcode"));
	                address.setstate(rs.getString("state"));
	                address.setcity(rs.getString("city"));
	                address.setstreet(rs.getString("street"));
	                contact.setaddress(address);
	            	contact.setcompany(rs.getString("company"));
	            	contact.settitle(rs.getString("title"));
	            	contact.setnote(rs.getString("note"));
	            	contact.setwebsite(rs.getString("website"));
	                return contact;
	            }	 
	            return null;
	        }
        });
	}
    
    public Contact findByMobile(String mobile) {
        String sql = "SELECT contactid, userid, firstname, lastname, "
        		+ "mobile, home, work, email, birthday, (address).country AS country, "
        		+ "(address).zipcode AS zipcode, (address).state AS state, (address).city AS city, "
        		+ "(address).street AS street, company, title, note, website "
        		+ "FROM tblContact WHERE mobile='" + mobile + "'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Contact>() {
	 
	        @Override
	        public Contact extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Contact contact = new Contact();
	            	contact.setcontactid(rs.getInt("contactid"));
	            	contact.setuserid(rs.getInt("userid"));
	            	contact.setfirstname(rs.getString("firstname"));
	            	contact.setlastname(rs.getString("lastname"));
	            	contact.setmobile(rs.getString("mobile"));
	            	contact.sethome(rs.getString("home"));
	            	contact.setwork(rs.getString("work"));
	            	contact.setemail(rs.getString("email"));
	            	contact.setbirthday(rs.getDate("birthday"));
	                Address address = new Address();
	                address.setcountry(rs.getString("country"));
	                address.setzipcode(rs.getString("zipcode"));
	                address.setstate(rs.getString("state"));
	                address.setcity(rs.getString("city"));
	                address.setstreet(rs.getString("street"));
	                contact.setaddress(address);
	            	contact.setcompany(rs.getString("company"));
	            	contact.settitle(rs.getString("title"));
	            	contact.setnote(rs.getString("note"));
	            	contact.setwebsite(rs.getString("website"));
	                return contact;
	            }	 
	            return null;
	        }
        });
	}

    public boolean isExist(Contact contact) {
        return findByMobile(contact.getmobile())!=null;
    }
}