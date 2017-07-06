package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.UserTarget;
import com.SpringMVC.dao.UserTargetDAO;
import com.SpringMVC.mapper.UserTargetMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class UserTargetDAOImpl extends JdbcDaoSupport implements UserTargetDAO {
 
    @Autowired
    public UserTargetDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(UserTarget userTarget) {
        String sql = "INSERT INTO tblUserTarget "
        		+ "(userid, period, teamtargetid, prospect, testdrive, closed) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		userTarget.getuserid(), userTarget.getperiod(), userTarget.getteamtargetid(),
        		userTarget.getprospect(), userTarget.gettestdrive(), userTarget.getclosed());
    }
    
    public void update(UserTarget userTarget) {
        String sql = "UPDATE tblUserTarget SET prospect=?, testdrive=?, closed=? "
        		+ "WHERE targetid=?";
        this.getJdbcTemplate().update(sql, 
        		userTarget.getprospect(), userTarget.gettestdrive(), userTarget.getclosed(), 
        		userTarget.gettargetid());
    }

    public void delete(int targetid) {
        String sql = "DELETE FROM tblUserTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public UserTarget get(int targetid) {
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
	    		+ "WHERE ut.targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserTarget>() {
	 
	        @Override
	        public UserTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserTarget userTarget = new UserTarget();
	                userTarget.settargetid(rs.getInt("targetid"));
	                userTarget.setuserid(rs.getInt("userid"));
	                userTarget.setusername(rs.getString("username"));	                
	                userTarget.setperiod(rs.getString("period"));
	                userTarget.setteamtargetid(rs.getInt("teamtargetid"));
	                userTarget.setprospect(rs.getInt("prospect"));
	                userTarget.settestdrive(rs.getInt("testdrive"));
	                userTarget.setclosed(rs.getInt("closed"));
	                return userTarget;
	            }	 
	            return null;
	        }
        });
    }

    public UserTarget getByPeriod(String period, int userid) {
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
	    		+ "WHERE ut.period='"+period+"' "
	    		+ "AND ut.userid="+userid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserTarget>() {
	 
	        @Override
	        public UserTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserTarget userTarget = new UserTarget();
	                userTarget.settargetid(rs.getInt("targetid"));
	                userTarget.setuserid(rs.getInt("userid"));
	                userTarget.setusername(rs.getString("username"));	                
	                userTarget.setperiod(rs.getString("period"));
	                userTarget.setteamtargetid(rs.getInt("teamtargetid"));
	                userTarget.setprospect(rs.getInt("prospect"));
	                userTarget.settestdrive(rs.getInt("testdrive"));
	                userTarget.setclosed(rs.getInt("closed"));
	                return userTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<UserTarget> list(String period, int teamid) {
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
        		+ "WHERE ut.period = '"+period+"' "
        		+ "AND u.teamid ="+teamid;
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<UserTarget> listByCompany(int companyid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
        		+ "WHERE u.companyid = " + companyid + " "
	    		+ "AND period >= '" + period + "' ";
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<UserTarget> listByBranch(int branchid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
        		+ "WHERE u.branchid = " + branchid + " "
	    		+ "AND period >= '" + period + "' ";
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<UserTarget> listByTeam(int teamid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
        		+ "WHERE u.teamid = " + teamid + " "
	    		+ "AND period >= '" + period + "' ";
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<UserTarget> listByUser(int userid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
	    String sql = "SELECT ut.targetid AS targetid, "
	    		+ "ut.userid AS userid, "
	    		+ "u.username AS username, "
	    		+ "ut.period AS period, "
	    		+ "ut.teamtargetid AS teamtargetid, "
	    		+ "ut.prospect AS prospect, "
	    		+ "ut.testdrive AS testdrive, "
	    		+ "ut.closed AS closed "
	    		+ "FROM tblUserTarget ut "
	    		+ "LEFT JOIN tblUser u ON u.userid = ut.userid "	    		
        		+ "WHERE ut.userid = " + userid + " "
	    		+ "AND period >= '" + period + "' ";
        UserTargetMapper mapper = new UserTargetMapper();
        List<UserTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}