package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.ClosingPeriod;
import com.SpringMVC.dao.ClosingPeriodDAO;
import com.SpringMVC.mapper.ClosingPeriodMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ClosingPeriodDAOImpl extends JdbcDaoSupport implements ClosingPeriodDAO {
 
    @Autowired
    public ClosingPeriodDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(ClosingPeriod closingPeriod) {
        String sql = "INSERT INTO tblClosingPeriod "
        		+ "(companyid, period, opendate, closedate, closed) "
        		+ "VALUES (?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		closingPeriod.getcompanyid(), closingPeriod.getperiod(), 
        		closingPeriod.getopendate(), closingPeriod.getclosedate(), 
        		closingPeriod.getclosed());
    }
    
    public void update(ClosingPeriod closingPeriod) {
        String sql = "UPDATE tblClosingPeriod SET opendate=?, closedate=?, closed=? "
        		+ "WHERE id=?";
        this.getJdbcTemplate().update(sql, 
        		closingPeriod.getopendate(), closingPeriod.getclosedate(), 
        		closingPeriod.getclosed(), closingPeriod.getid());
    }

    public void delete(int id) {
        String sql = "DELETE FROM tblClosingPeriod WHERE id=?";
        this.getJdbcTemplate().update(sql, id);
    }
    
    public ClosingPeriod get(int id) {
	    String sql = "SELECT id, companyid, period, "
	    		+ "opendate, closedate, closed "
	    		+ "FROM tblClosingPeriod "
	    		+ "WHERE id="+id;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<ClosingPeriod>() {
	 
			@Override
	        public ClosingPeriod extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	ClosingPeriod closingPeriod = new ClosingPeriod();
	            	closingPeriod.setid(rs.getInt("id"));
	            	closingPeriod.setcompanyid(rs.getInt("companyid"));
	            	closingPeriod.setperiod(rs.getString("period"));
	            	closingPeriod.setopendate(rs.getDate("opendate"));
	            	closingPeriod.setclosedate(rs.getDate("closedate"));
	            	closingPeriod.setclosed(rs.getBoolean("closed"));
	                return closingPeriod;
	            }	 
	            return null;
	        }
        });
    }

    @Override
    public String getCurrentPeriod(int companyid) {
    	String sql = "SELECT spGetCurrentPeriod(?) ";
//      String sql = "EXEC spGetCurrentPeriod ? ";
        String period = (String)getJdbcTemplate().queryForObject(sql, new Object[] {companyid}, String.class);
        return period;
    }

    public List<ClosingPeriod> list(int companyid) {
    	//only get data greater than or equal current month - 3
        int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        String period = "";
        if (currentmonth<4){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+9);
        };
        if (currentmonth>3){
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        };
	    String sql = "SELECT id, companyid, period, "
	    		+ "opendate, closedate, closed "
	    		+ "FROM tblClosingPeriod "	    
	    		+ "WHERE companyid=" + companyid + " "
	    		+ "AND period>='" + period + "' "
				+ "ORDER BY period";
        ClosingPeriodMapper mapper = new ClosingPeriodMapper();
        List<ClosingPeriod> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    @Override
    public List<String> getPeriod(int companyid) {
        String sql = "Select period FROM tblClosingPeriod "
        		+ "WHERE companyid = " + companyid;
         
        List<String> periods = this.getJdbcTemplate().queryForList(sql, String.class);         
        return periods;
    }
}