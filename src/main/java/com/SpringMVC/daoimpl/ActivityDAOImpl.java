package com.SpringMVC.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Activity;
import com.SpringMVC.dao.ActivityDAO;
import com.SpringMVC.mapper.ActivityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ActivityDAOImpl extends JdbcDaoSupport implements ActivityDAO {
 
    @Autowired
    public ActivityDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Activity activity) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spActivityInsUpd("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
	    			+ "?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "0");
	    	proc.setInt(3, activity.getactivityid());
	    	proc.setInt(4, activity.getuserid());
	    	proc.setInt(5, activity.getprospectid());
	    	proc.setDate(6, activity.getactivitydate());
	    	proc.setTime(7, activity.getactivitytime());
	    	proc.setInt(8, activity.getbrandid());
	    	proc.setInt(9, activity.getmodelid());
	    	proc.setBoolean(10, activity.getdemo());
	    	proc.setDate(11, activity.getdemodate());
	    	proc.setTime(12, activity.getdemotime());
	    	proc.setBoolean(13, activity.gettestdrive());
	    	proc.setDate(14, activity.gettestdrivedate());
	    	proc.setTime(15, activity.gettestdrivetime());
	    	proc.setBoolean(16, activity.getquotation());
	    	proc.setDate(17, activity.getquotationdate());
	    	proc.setTime(18, activity.getquotationtime());
	    	proc.setInt(19, activity.getquotationid());
	    	proc.setBoolean(20, activity.getclosed());
	    	proc.setDate(21, activity.getcloseddate());
	    	proc.setTime(22, activity.getclosedtime());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void update(Activity activity) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spActivityInsUpd("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "1");
	    	proc.setInt(3, activity.getactivityid());
	    	proc.setInt(4, activity.getuserid());
	    	proc.setInt(5, activity.getprospectid());
	    	proc.setDate(6, activity.getactivitydate());
	    	proc.setTime(7, activity.getactivitytime());
	    	proc.setInt(8, activity.getbrandid());
	    	proc.setInt(9, activity.getmodelid());
	    	proc.setBoolean(10, activity.getdemo());
	    	proc.setDate(11, activity.getdemodate());
	    	proc.setTime(12, activity.getdemotime());
	    	proc.setBoolean(13, activity.gettestdrive());
	    	proc.setDate(14, activity.gettestdrivedate());
	    	proc.setTime(15, activity.gettestdrivetime());
	    	proc.setBoolean(16, activity.getquotation());
	    	proc.setDate(17, activity.getquotationdate());
	    	proc.setTime(18, activity.getquotationtime());
	    	proc.setInt(19, activity.getquotationid());
	    	proc.setBoolean(20, activity.getclosed());
	    	proc.setDate(21, activity.getcloseddate());
	    	proc.setTime(22, activity.getclosedtime());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }

    public void delete(int activityid) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spActivityDel(?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, activityid);
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public List<Activity> list(int prospectid) {
        String sql = "SELECT a.activityid, a.userid, u.username, "
        		+ "a.prospectid, p.firstname, p.lastname, "
        		+ "a.activitydate, a.activitytime, "
        		+ "a.brandid, a.modelid, b.brandname, m.modelname, "
        		+ "a.demo, a.demodate, a.demotime, "
        		+ "a.testdrive, a.testdrivedate, a.testdrivetime, "
        		+ "a.quotation, a.quotationdate, a.quotationtime, "
        		+ "a.quotationid, a.quotationpdflink, "
        		+ "a.closed, a.closeddate, a.closedtime "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = a.userid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE a.prospectid = " + prospectid + " "
        		+ "ORDER BY a.activitydate, a.activitytime ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Activity> listByUser(int userid) {
        String sql = "SELECT a.activityid, a.userid, u.username, "
        		+ "a.prospectid, p.firstname, p.lastname, "
        		+ "a.activitydate, a.activitytime, "
        		+ "a.brandid, a.modelid, b.brandname, m.modelname, "
        		+ "a.demo, a.demodate, a.demotime, "
        		+ "a.testdrive, a.testdrivedate, a.testdrivetime, "
        		+ "a.quotation, a.quotationdate, a.quotationtime, "
        		+ "a.quotationid, a.quotationpdflink, "
        		+ "a.closed, a.closeddate, a.closedtime "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = a.userid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE a.userid = " + userid + " "
        		+ "ORDER BY a.activitydate, a.activitytime ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Activity> listByTeam(int teamid) {
        String sql = "SELECT a.activityid, a.userid, u.username, "
        		+ "a.prospectid, p.firstname, p.lastname, "
        		+ "a.activitydate, a.activitytime, "
        		+ "a.brandid AS brandid, a.modelid AS modelid, "
        		+ "b.brandname AS brandname, m.modelname AS modelname, "
        		+ "demo, demodate, demotime, "
        		+ "testdrive, testdrivedate, testdrivetime, "
        		+ "quotation, quotationdate, quotationtime, "
        		+ "a.quotationid, a.quotationpdflink, "
        		+ "a.closed, a.closeddate, a.closedtime "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = a.userid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE u.teamid = " + teamid + " "
        		+ "ORDER BY a.activitydate, a.activitytime";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Activity> listByBranch(int branchid) {
        String sql = "SELECT a.activityid, a.userid, u.username, "
        		+ "a.prospectid, p.firstname, p.lastname, "
        		+ "a.activitydate, a.activitytime, "
        		+ "a.brandid AS brandid, a.modelid AS modelid, "
        		+ "b.brandname AS brandname, m.modelname AS modelname, "
        		+ "a.demo, a.demodate, a.demotime, "
        		+ "a.testdrive, a.testdrivedate, a.testdrivetime, "
        		+ "a.quotation, a.quotationdate, a.quotationtime, "
        		+ "a.quotationid, a.quotationpdflink, "
        		+ "a.closed, a.closeddate, a.closedtime "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = a.userid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE u.branchid = " + branchid + " "
        		+ "ORDER BY a.activitydate, a.activitytime ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Activity> listByCompany(int companyid) {
        String sql = "SELECT a.activityid, a.userid, u.username AS username, "
        		+ "a.prospectid, p.firstname, p.lastname, "
        		+ "a.activitydate, a.activitytime, "
        		+ "a.brandid, a.modelid, b.brandname, m.modelname, "
        		+ "a.demo, a.demodate, demotime, "
        		+ "a.testdrive, a.testdrivedate, a.testdrivetime, "
        		+ "a.quotation, a.quotationdate, a.quotationtime, "
        		+ "a.quotationid, a.quotationpdflink, "
        		+ "a.closed, a.closeddate, a.closedtime "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = a.userid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE u.companyid = " + companyid + " "
        		+ "ORDER BY a.activitydate, a.activitytime ";
        ActivityMapper mapper = new ActivityMapper();
        List<Activity> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Activity get(int activityid) {
        String sql = "SELECT a.activityid, a.userid, u.username, "
        		+ "a.prospectid, p.firstname, p.lastname, "
        		+ "a.activitydate, a.activitytime, "
        		+ "a.brandid, a.modelid, b.brandname, m.modelname, "
        		+ "a.demo, a.demodate, a.demotime, "
        		+ "a.testdrive, a.testdrivedate, a.testdrivetime, "
        		+ "a.quotation, a.quotationdate, a.quotationtime, "
        		+ "a.quotationid, a.quotationpdflink, "
        		+ "a.closed, a.closeddate, a.closedtime "
        		+ "FROM tblActivity a "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = a.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = a.userid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
	    		+ "WHERE activityid=" + activityid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Activity>() {
	 
	        @Override
	        public Activity extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Activity activity = new Activity();
	                activity.setactivityid(rs.getInt("activityid"));
	                activity.setuserid(rs.getInt("userid"));
	                activity.setusername(rs.getString("username"));
	                activity.setprospectid(rs.getInt("prospectid"));
	                activity.setfirstname(rs.getString("firstname"));
	                activity.setlastname(rs.getString("lastname"));
	                activity.setactivitydate(rs.getDate("activitydate"));
	                activity.setactivitytime(rs.getTime("activitytime"));
	                activity.setbrandid(rs.getInt("brandid"));
	                activity.setbrandname(rs.getString("brandname"));
	                activity.setmodelid(rs.getInt("modelid"));
	                activity.setmodelname(rs.getString("modelname"));
	                activity.setdemo(rs.getBoolean("demo"));
	                activity.setdemodate(rs.getDate("demodate"));
	                activity.setdemotime(rs.getTime("demotime"));
	                activity.settestdrive(rs.getBoolean("testdrive"));
	                activity.settestdrivedate(rs.getDate("testdrivedate"));
	                activity.settestdrivetime(rs.getTime("testdrivetime"));
	                activity.setquotation(rs.getBoolean("quotation"));
	                activity.setquotationdate(rs.getDate("quotationdate"));
	                activity.setquotationtime(rs.getTime("quotationtime"));
	                activity.setquotationid(rs.getInt("quotationid"));
	                activity.setquotationpdflink(rs.getString("quotationpdflink"));
	                activity.setclosed(rs.getBoolean("closed"));
	                activity.setcloseddate(rs.getDate("closeddate"));
	                activity.setclosedtime(rs.getTime("closedtime"));
	                return activity;
	            }	 
	            return null;
	        }
        });
    }

    public int getlastactivityid(int prospectid) {
    	String sql = "SELECT MAX(activityid) FROM tblActivity WHERE prospectid = ?";
        int activityid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {prospectid}, int.class);
        return activityid;
    }
}