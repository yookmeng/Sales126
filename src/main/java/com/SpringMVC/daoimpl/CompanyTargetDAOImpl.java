package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.CompanyTarget;
import com.SpringMVC.dao.CompanyTargetDAO;
import com.SpringMVC.mapper.CompanyTargetMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class CompanyTargetDAOImpl extends JdbcDaoSupport implements CompanyTargetDAO {
 
    @Autowired
    public CompanyTargetDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(CompanyTarget companyTarget) {
        String sql = "INSERT INTO tblCompanyTarget "
        		+ "(companyid, period, prospect, testdrive, closed) "
        		+ "VALUES (?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		companyTarget.getcompanyid(), companyTarget.getperiod(), 
        		companyTarget.getprospect(), companyTarget.gettestdrive(), companyTarget.getclosed());
    }
    
    public void update(CompanyTarget companyTarget) {
        String sql = "UPDATE tblCompanyTarget SET prospect=?, testdrive=?, closed=? "
        		+ "WHERE targetid=?";
        this.getJdbcTemplate().update(sql, 
        		companyTarget.getprospect(), companyTarget.gettestdrive(), companyTarget.getclosed(), 
        		companyTarget.gettargetid());
    }

    public void delete(int targetid) {
        String sql = "DELETE FROM tblCompanyTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public CompanyTarget get(int targetid) {
	    String sql = "SELECT ct.targetid AS targetid, "
	    		+ "ct.companyid AS companyid, "
	    		+ "c.companyname AS companyname, "
	    		+ "ct.period AS period, "
	    		+ "ct.prospect AS prospect, "
	    		+ "ct.testdrive AS testdrive, "
	    		+ "ct.closed AS closed "
	    		+ "FROM tblCompanyTarget ct "
	    		+ "LEFT JOIN tblCompany c ON c.companyid = ct.companyid "
	    		+ "WHERE ct.targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<CompanyTarget>() {
	 
			@Override
	        public CompanyTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                CompanyTarget companyTarget = new CompanyTarget();
	                companyTarget.settargetid(rs.getInt("targetid"));
	                companyTarget.setcompanyid(rs.getInt("companyid"));
	                companyTarget.setcompanyname(rs.getString("companyname"));
	                companyTarget.setperiod(rs.getString("period"));
	                companyTarget.setprospect(rs.getInt("prospect"));
	                companyTarget.settestdrive(rs.getInt("testdrive"));
	                companyTarget.setclosed(rs.getInt("closed"));
	                return companyTarget;
	            }	 
	            return null;
	        }
        });
    }

    public CompanyTarget getByPeriod(String period, int companyid) {
	    String sql = "SELECT ct.targetid AS targetid, "
	    		+ "ct.companyid AS companyid, "
	    		+ "c.companyname AS companyname, "
	    		+ "ct.period AS period, "
	    		+ "ct.prospect AS prospect, "
	    		+ "ct.testdrive AS testdrive, "
	    		+ "ct.closed AS closed "
	    		+ "FROM tblCompanyTarget ct "
	    		+ "LEFT JOIN tblCompany c ON c.companyid = ct.companyid "
	    		+ "WHERE ct.period = '" + period + "' "
				+ "AND ct.companyid = " + companyid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<CompanyTarget>() {
	 
			@Override
	        public CompanyTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                CompanyTarget companyTarget = new CompanyTarget();
	                companyTarget.settargetid(rs.getInt("targetid"));
	                companyTarget.setcompanyid(rs.getInt("companyid"));
	                companyTarget.setcompanyname(rs.getString("companyname"));
	                companyTarget.setperiod(rs.getString("period"));
	                companyTarget.setprospect(rs.getInt("prospect"));
	                companyTarget.settestdrive(rs.getInt("testdrive"));
	                companyTarget.setclosed(rs.getInt("closed"));
	                return companyTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<CompanyTarget> list(int companyid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
	    String sql = "SELECT ct.targetid AS targetid, "
	    		+ "ct.companyid AS companyid, "
	    		+ "c.companyname AS companyname, "
	    		+ "ct.period AS period, "
	    		+ "ct.prospect AS prospect, "
	    		+ "ct.testdrive AS testdrive, "
	    		+ "ct.closed AS closed "
	    		+ "FROM tblCompanyTarget ct "
	    		+ "LEFT JOIN tblCompany c ON c.companyid = ct.companyid "
        		+ "WHERE ct.companyid=" + companyid + " "
				+ "and ct.period>='" + period + "'";
	    
        CompanyTargetMapper mapper = new CompanyTargetMapper();
        List<CompanyTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}