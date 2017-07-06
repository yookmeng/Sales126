package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.TeamTarget;
import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.mapper.TeamTargetMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class TeamTargetDAOImpl extends JdbcDaoSupport implements TeamTargetDAO {
 
    @Autowired
    public TeamTargetDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(TeamTarget teamTarget) {
        String sql = "INSERT INTO tblTeamTarget "
        		+ "(teamid, period, branchtargetid, prospect, testdrive, closed) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		teamTarget.getteamid(), teamTarget.getperiod(), teamTarget.getbranchtargetid(), 
        		teamTarget.getprospect(), teamTarget.gettestdrive(), teamTarget.getclosed());
    }
    
    public void update(TeamTarget teamTarget) {
        String sql = "UPDATE tblTeamTarget SET prospect=?, testdrive=?, closed=? "
        		+ "WHERE teamid=? AND period=?";
        this.getJdbcTemplate().update(sql, 
        		teamTarget.getprospect(), teamTarget.gettestdrive(), teamTarget.getclosed(), 
        		teamTarget.getteamid(), teamTarget.getperiod());
    }
    
    public void delete(int targetid) {
        String sql = "DELETE FROM tblTeamTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public TeamTarget get(int targetid) {
	    String sql = "SELECT tt.targetid AS targetid, "
	    		+ "tt.teamid AS teamid, "
	    		+ "t.teamname AS teamname, "
	    		+ "tt.period AS period, "
	    		+ "tt.branchtargetid AS branchtargetid, "
	    		+ "tt.prospect AS prospect, "
	    		+ "tt.testdrive AS testdrive, "
	    		+ "tt.closed AS closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
	    		+ "WHERE tt.targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<TeamTarget>() {
	 
	        @Override
	        public TeamTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                TeamTarget teamTarget = new TeamTarget();
	                teamTarget.settargetid(rs.getInt("targetid"));
	                teamTarget.setteamid(rs.getInt("teamid"));
	                teamTarget.setteamname(rs.getString("teamname"));
	                teamTarget.setperiod(rs.getString("period"));
	                teamTarget.setbranchtargetid(rs.getInt("branchtargetid"));
	                teamTarget.setprospect(rs.getInt("prospect"));
	                teamTarget.settestdrive(rs.getInt("testdrive"));
	                teamTarget.setclosed(rs.getInt("closed"));
	                return teamTarget;
	            }	 
	            return null;
	        }
        });
    }

    public TeamTarget getByPeriod(String period, int teamid) {
	    String sql = "SELECT tt.targetid AS targetid, "
	    		+ "tt.teamid AS teamid, "
	    		+ "t.teamname AS teamname, "
	    		+ "tt.period AS period, "
	    		+ "tt.branchtargetid AS branchtargetid, "
	    		+ "tt.prospect AS prospect, "
	    		+ "tt.testdrive AS testdrive, "
	    		+ "tt.closed AS closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
	    		+ "WHERE tt.period='"+period + "' "
	    		+ "AND tt.teamid="+teamid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<TeamTarget>() {
	 
	        @Override
	        public TeamTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                TeamTarget teamTarget = new TeamTarget();
	                teamTarget.settargetid(rs.getInt("targetid"));
	                teamTarget.setteamid(rs.getInt("teamid"));
	                teamTarget.setteamname(rs.getString("teamname"));
	                teamTarget.setperiod(rs.getString("period"));
	                teamTarget.setbranchtargetid(rs.getInt("branchtargetid"));
	                teamTarget.setprospect(rs.getInt("prospect"));
	                teamTarget.settestdrive(rs.getInt("testdrive"));
	                teamTarget.setclosed(rs.getInt("closed"));
	                return teamTarget;
	            }	 
	            return null;
	        }
        });
    }

    public TeamTarget getByTeam(int teamid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
	    String sql = "SELECT tt.targetid AS targetid, "
	    		+ "tt.teamid AS teamid, "
	    		+ "t.teamname AS teamname, "
	    		+ "tt.period AS period, "
	    		+ "tt.branchtargetid AS branchtargetid, "
	    		+ "tt.prospect AS prospect, "
	    		+ "tt.testdrive AS testdrive, "
	    		+ "tt.closed AS closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
	    		+ "WHERE tt.teamid=" + teamid + " "
	    		+ "AND tt.period>='" + period + "' ";        		
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<TeamTarget>() {
	 
	        @Override
	        public TeamTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                TeamTarget teamTarget = new TeamTarget();
	                teamTarget.settargetid(rs.getInt("targetid"));
	                teamTarget.setteamid(rs.getInt("teamid"));
	                teamTarget.setteamname(rs.getString("teamname"));
	                teamTarget.setperiod(rs.getString("period"));
	                teamTarget.setbranchtargetid(rs.getInt("branchtargetid"));
	                teamTarget.setprospect(rs.getInt("prospect"));
	                teamTarget.settestdrive(rs.getInt("testdrive"));
	                teamTarget.setclosed(rs.getInt("closed"));
	                return teamTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<TeamTarget> list(String period, int branchid) {
	    String sql = "SELECT tt.targetid AS targetid, "
	    		+ "tt.teamid AS teamid, "
	    		+ "t.teamname AS teamname, "
	    		+ "tt.period AS period, "
	    		+ "tt.branchtargetid AS branchtargetid, "
	    		+ "tt.prospect AS prospect, "
	    		+ "tt.testdrive AS testdrive, "
	    		+ "tt.closed AS closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
        		+ "WHERE tt.period = '" + period + "' "
        		+ "AND t.branchid = " + branchid + " "
        		+ "ORDER BY t.teamname";
        TeamTargetMapper mapper = new TeamTargetMapper();
        List<TeamTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<TeamTarget> listAll(String period, int companyid) {
	    String sql = "SELECT tt.targetid AS targetid, "
	    		+ "tt.teamid AS teamid, "
	    		+ "t.teamname AS teamname, "
	    		+ "tt.period AS period, "
	    		+ "tt.branchtargetid AS branchtargetid, "
	    		+ "tt.prospect AS prospect, "
	    		+ "tt.testdrive AS testdrive, "
	    		+ "tt.closed AS closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
        		+ "LEFT JOIN tblBranch b ON b.branchid = t.branchid "	    		
        		+ "WHERE tt.period = '" + period + "' "
        		+ "AND b.companyid = " + companyid + " "
        		+ "ORDER BY b.branchname, t.teamname";
        TeamTargetMapper mapper = new TeamTargetMapper();
        List<TeamTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<TeamTarget> listByCompany(int companyid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
	    String sql = "SELECT tt.targetid AS targetid, "
	    		+ "tt.teamid AS teamid, "
	    		+ "t.teamname AS teamname, "
	    		+ "tt.period AS period, "
	    		+ "tt.branchtargetid AS branchtargetid, "
	    		+ "tt.prospect AS prospect, "
	    		+ "tt.testdrive AS testdrive, "
	    		+ "tt.closed AS closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
        		+ "LEFT JOIN tblBranch b ON b.branchid = t.branchid "	    		
        		+ "WHERE b.companyid = " + companyid + " "
 				+ "AND tt.period>='" + period + "' "        		
        		+ "ORDER BY period";

        TeamTargetMapper mapper = new TeamTargetMapper();
        List<TeamTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<TeamTarget> listByBranch(int branchid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
	    String sql = "SELECT tt.targetid AS targetid, "
	    		+ "tt.teamid AS teamid, "
	    		+ "t.teamname AS teamname, "
	    		+ "tt.period AS period, "
	    		+ "tt.branchtargetid AS branchtargetid, "
	    		+ "tt.prospect AS prospect, "
	    		+ "tt.testdrive AS testdrive, "
	    		+ "tt.closed AS closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
        		+ "WHERE t.branchid = " + branchid + " "
 				+ "AND tt.period>='" + period + "' "        		
        		+ "ORDER BY period";

        TeamTargetMapper mapper = new TeamTargetMapper();
        List<TeamTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<TeamTarget> listByTeam(int teamid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
	    String sql = "SELECT tt.targetid AS targetid, "
	    		+ "tt.teamid AS teamid, "
	    		+ "t.teamname AS teamname, "
	    		+ "tt.period AS period, "
	    		+ "tt.branchtargetid AS branchtargetid, "
	    		+ "tt.prospect AS prospect, "
	    		+ "tt.testdrive AS testdrive, "
	    		+ "tt.closed AS closed "
	    		+ "FROM tblTeamTarget tt "
        		+ "LEFT JOIN tblTeam t ON t.teamid = tt.teamid "	    		
        		+ "WHERE t.teamid = " + teamid + " "
 				+ "AND tt.period>='" + period + "' "        		
        		+ "ORDER BY period";

        TeamTargetMapper mapper = new TeamTargetMapper();
        List<TeamTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}