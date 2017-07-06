package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource; 
import com.SpringMVC.dao.CountryDAO;
import com.SpringMVC.mapper.CountryMapper;
import com.SpringMVC.model.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class CountryDAOImpl extends JdbcDaoSupport implements CountryDAO {
 
    @Autowired
    public CountryDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Country country) {
        String sql = "INSERT INTO tblCountry (countrycode, countryname, iddcode) VALUES (?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		country.getcountrycode(), country.getcountryname(), country.getiddcode());
    }
    
    public void update(Country country) {
        String sql = "UPDATE tblCountry SET countrycode=?, countryname=?, iddcode=? "
        		   + "WHERE countryid=?";
        this.getJdbcTemplate().update(sql, country.getcountrycode(), 
        		country.getcountryname(), country.getiddcode(), country.getcountryid());
    }
    
    public void delete(int countryid) {
        String sql = "DELETE FROM tblCountry WHERE countryid=?";
        this.getJdbcTemplate().update(sql, countryid);
    }
    
    public Country get(int countryid) {
	    String sql = "SELECT countryid, countrycode, countryname, iddcode "
	    		   + "FROM tblCountry WHERE countryid="+countryid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Country>() {
	 
			@Override
	        public Country extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Country country = new Country();
	            	country.setcountryid(rs.getInt("countryid"));
	            	country.setcountrycode(rs.getString("countrycode"));
	            	country.setcountryname(rs.getString("countryname"));
	            	country.setiddcode(rs.getString("iddcode"));
	                return country;
	            }	 
	            return null;
	        }
        });
    }
    
    public List<Country> list() {
	    String sql = "SELECT countryid, countrycode, countryname, iddcode FROM tblCountry";
        CountryMapper mapper = new CountryMapper();
        List<Country> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }                    
}