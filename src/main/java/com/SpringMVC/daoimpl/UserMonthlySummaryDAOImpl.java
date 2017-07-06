package com.SpringMVC.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import com.SpringMVC.model.UserMonthlySummary;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
import com.SpringMVC.mapper.UserMonthlySummaryMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository 
public class UserMonthlySummaryDAOImpl extends JdbcDaoSupport implements UserMonthlySummaryDAO {
 
    @Autowired
    public UserMonthlySummaryDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
    
	private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

	public UserMonthlySummary get(String period, int userid, String userRole) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spGenMonthlySummary(?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, userid);
	    	proc.setString(3, period);
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	String sql = "";
		Roles role = Roles.valueOf(userRole); 
		switch (role){
			case USER:
			    sql = "SELECT period, userid, username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE period = '" + period + "' "
						+ "AND userid = " + userid;
				break;
			case TL:
		        sql = "Select teamid from tblTeam where leaderid = ? ";
		        int teamid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);         

			    sql = "SELECT period, ms.userid, ms.username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE period = '" + period + "' "
						+ "AND ms.teamid = " + teamid + " "
						+ "ORDER BY ms.username";
				break;
			case MA:
		        sql = "Select branchid from tblBranch where maid = ? ";
		        int branchid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);         
			    sql = "SELECT period, ms.userid, ms.username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE period = '" + period + "' "
						+ "AND ms.branchid = " + branchid + " "
						+ "ORDER BY t.teamname, ms.username";
				break;
			case MD:
		        sql = "Select companyid from tblCompany where mdid = ? ";
		        int companyid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);         
			    sql = "SELECT period, ms.userid, ms.username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE period = '" + period + "' "
						+ "AND ms.companyid = " + companyid + " "
						+ "ORDER BY b.branchname, t.teamname, ms.username";
				break;
			default:
		}
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserMonthlySummary>() {

	        @Override
	        public UserMonthlySummary extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	        	if (rs.next()) {
	            	UserMonthlySummary userMonthlySummary = new UserMonthlySummary();
	            	userMonthlySummary.setperiod(rs.getString("period"));
	            	userMonthlySummary.setuserid(rs.getInt("userid"));
	            	userMonthlySummary.setusername(rs.getString("username"));
	            	userMonthlySummary.setteamid(rs.getInt("teamid"));
	            	userMonthlySummary.setteamname(rs.getString("teamname"));
	            	userMonthlySummary.setbranchid(rs.getInt("branchid"));
	            	userMonthlySummary.setbranchname(rs.getString("branchname"));
	            	userMonthlySummary.setcompanyid(rs.getInt("companyid"));
	            	userMonthlySummary.setcompanyname(rs.getString("companyname"));
	            	userMonthlySummary.settargetid(rs.getInt("targetid"));
	            	userMonthlySummary.settargetprospect(rs.getInt("targetprospect"));
	            	userMonthlySummary.settargettestdrive(rs.getInt("targettestdrive"));
	            	userMonthlySummary.settargetclosed(rs.getInt("targetclosed"));
	            	userMonthlySummary.setactualprospect(rs.getInt("actualprospect"));
	            	userMonthlySummary.setactualtestdrive(rs.getInt("actualtestdrive"));
	            	userMonthlySummary.setactualclosed(rs.getInt("actualclosed"));
	            	userMonthlySummary.setpercentprospect(rs.getFloat("percentprospect"));
	            	userMonthlySummary.setpercenttestdrive(rs.getFloat("percenttestdrive"));
	            	userMonthlySummary.setpercentclosed(rs.getFloat("percentclosed"));
	            	userMonthlySummary.setcommission(rs.getFloat("commission"));
	            	userMonthlySummary.settotalhot(rs.getInt("totalhot"));
	            	userMonthlySummary.setpendingactivity(rs.getInt("pendingactivity"));
	                return userMonthlySummary;
	            }
	            return null;
	        }
        });                
    }

    public List<UserMonthlySummary> list(String period, int userid, String userRole) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spGenMonthlySummary(?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, userid);
	    	proc.setString(3, period);
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	String sql = "";
		Roles role = Roles.valueOf(userRole); 
		switch (role){
			case USER:
			    sql = "SELECT period, userid, username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE userid = " + userid + " AND period = '" + period +"'";
				break;
			case TL:
		        sql = "Select teamid from tblTeam where leaderid = ? ";
		        int teamid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);         

			    sql = "SELECT period, ms.userid, ms.username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE ms.teamid = " + teamid + " AND period = '" + period +"'"
	    				+ "ORDER BY ms.userid";
				break;
			case MA:
		        sql = "Select branchid from tblBranch where maid = ? ";
		        int branchid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);         
			    sql = "SELECT period, ms.userid, ms.username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE ms.branchid = " + branchid + " AND period = '" + period +"'"
	    				+ "ORDER BY ms.teamid, ms.userid";
				break;
			case MD:
		        sql = "Select companyid from tblCompany where mdid = ? ";
		        int companyid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);         
			    sql = "SELECT period, ms.userid, ms.username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE ms.companyid = " + companyid + " AND period = '" + period +"'"
	    				+ "ORDER BY ms.branchid, ms.teamid, ms.userid ";
				break;
			default:
		}    	
		UserMonthlySummaryMapper mapper = new UserMonthlySummaryMapper();
        List<UserMonthlySummary> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<UserMonthlySummary> listAll(String period, int userid, String userRole) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spGenMonthlySummary(?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, userid);
	    	proc.setString(3, period);
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    	String sql = "";
		Roles role = Roles.valueOf(userRole); 
		switch (role){
			case USER:
			    sql = "SELECT period, userid, username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE userid = " + userid;
				break;
			case TL:
		        sql = "Select teamid from tblTeam where leaderid = ? ";
		        int teamid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);         

			    sql = "SELECT period, ms.userid, ms.username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE ms.teamid = " + teamid;
				break;
			case MA:
		        sql = "Select branchid from tblBranch where maid = ? ";
		        int branchid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);         
			    sql = "SELECT period, ms.userid, ms.username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE ms.branchid = " + branchid;
				break;
			case MD:
		        sql = "Select companyid from tblCompany where mdid = ? ";
		        int companyid = (int)getJdbcTemplate().queryForObject(sql, new Object[] {userid}, int.class);         
			    sql = "SELECT period, ms.userid, ms.username, ms.teamid, t.teamname, "
			    		+ "ms.branchid, b.branchname, ms.companyid, c.companyname, "
			    		+ "targetid, targetprospect, targettestdrive, targetclosed, "
			    		+ "actualprospect, actualtestdrive, actualclosed, "
			    		+ "percentprospect, percenttestdrive, percentclosed, "
			    		+ "commission, totalhot, pendingactivity "
			    		+ "FROM tblMonthlySummary ms "
			    		+ "LEFT JOIN tblTeam t ON ms.teamid = t.teamid "
			    		+ "LEFT JOIN tblBranch b ON ms.branchid = b.branchid "
			    		+ "LEFT JOIN tblCompany c ON ms.companyid = c.companyid "
			    		+ "WHERE ms.companyid = " + companyid;
				break;
			default:
		}    	
		UserMonthlySummaryMapper mapper = new UserMonthlySummaryMapper();
        List<UserMonthlySummary> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}