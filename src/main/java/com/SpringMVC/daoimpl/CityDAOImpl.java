package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource; 
import com.SpringMVC.dao.CityDAO;
import com.SpringMVC.mapper.CityMapper;
import com.SpringMVC.model.City;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class CityDAOImpl extends JdbcDaoSupport implements CityDAO {
 
    @Autowired
    public CityDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(City city) {
        String sql = "INSERT INTO tblCity (countryid, stateid, cityname) VALUES (?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		city.getcountryid(), city.getstateid(), city.getcityname());
    }
    
    public void update(City city) {
        String sql = "UPDATE tblCity SET cityname=? WHERE cityid=?";
        this.getJdbcTemplate().update(sql, city.getcityname(), city.getcityid());
    }
    
    public void delete(int cityid) {
        String sql = "DELETE FROM tblCity WHERE cityid=?";
        this.getJdbcTemplate().update(sql, cityid);
    }
    
    public City get(int cityid) {
	    String sql = "SELECT ct.cityid, ct.countryid, c.countryname, "
	    		   + "ct.stateid, s.statename, ct.cityname "
	    		   + "FROM tblCity ct "
	    		   + "LEFT JOIN tblCountry c ON c.countryid = ct.countryid "
	    		   + "LEFT JOIN tblState s ON s.stateid = ct.stateid "
	    		   + "WHERE ct.cityid="+cityid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<City>() {
	 
			@Override
	        public City extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	City city = new City();
	            	city.setcityid(rs.getInt("cityid"));
	            	city.setcountryid(rs.getInt("countryid"));
	            	city.setstateid(rs.getInt("stateid"));
	            	city.setcountryname(rs.getString("countryname"));
	            	city.setstatename(rs.getString("statename"));
	            	city.setcityname(rs.getString("cityname"));
	                return city;
	            }	 
	            return null;
	        }
        });
    }
    
    public List<City> list() {
	    String sql = "SELECT ct.cityid, ct.stateid, ct.countryid, "
	    		   + "c.countryname, s.statename, ct.cityname "
	    		   + "FROM tblCity ct "
	    		   + "LEFT JOIN tblCountry c ON c.countryid = ct.countryid "
	    		   + "LEFT JOIN tblState s ON s.stateid = ct.stateid ";
        CityMapper mapper = new CityMapper();
        List<City> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }                    
}