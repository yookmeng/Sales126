package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Team;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.mapper.TeamMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class TeamDAOImpl extends JdbcDaoSupport implements TeamDAO {
 
    @Autowired
    public TeamDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Team team) {
        String sql = "INSERT INTO tblTeam "
        		+ "(teamname, branchid, leaderid) "
        		+ "VALUES (?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		team.getteamname(), team.getbranchid(), team.getleaderid());
    }
    
    public void update(Team team) {
        String sql = "UPDATE tblTeam SET "
        		+ "teamname=?, leaderid=? "
        		+ "WHERE teamid=?";
        this.getJdbcTemplate().update(sql, 
        		team.getteamname(), team.getleaderid(), team.getteamid());
    }

    public void delete(int teamid) {
        String sql = "DELETE FROM tblTeam WHERE teamid=?";
        this.getJdbcTemplate().update(sql, teamid);
    }
    
    public List<Team> list(int branchid) {
        String sql = "SELECT t.teamid AS teamid, "
        		+ "t.teamname AS teamname, "
        		+ "t.branchid AS branchid, "
        		+ "t.leaderid AS leaderid, "
        		+ "l.username AS leadername "
        		+ "FROM tblTeam t "
	    		+ "LEFT JOIN tblUser l on t.leaderid = l.userid "
        		+ "WHERE t.branchid = " + branchid;
        TeamMapper mapper = new TeamMapper();
        List<Team> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<Team> listByCompany(int companyid) {
        String sql = "SELECT t.teamid AS teamid, "
        		+ "t.teamname AS teamname, "
        		+ "t.branchid AS branchid, "
        		+ "t.leaderid AS leaderid, "
        		+ "u.username AS leadername "
        		+ "FROM tblTeam t "
	    		+ "LEFT JOIN tblUser u on t.leaderid = u.userid "
        		+ "WHERE u.companyid = " + companyid;
        TeamMapper mapper = new TeamMapper();
        List<Team> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<String> teamList(int branchid) {
        String sql = "SELECT teamname FROM tblTeam WHERE branchid =" + branchid;
        List<String> list = this.getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    public Team get(int teamid) {
        String sql = "SELECT t.teamid AS teamid, "
        		+ "t.teamname AS teamname, "
        		+ "t.branchid AS branchid, "
        		+ "t.leaderid AS leaderid, "
        		+ "l.username AS leadername "
        		+ "FROM tblTeam t "
	    		+ "LEFT JOIN tblUser l on t.leaderid = l.userid "
	    		+ "WHERE t.teamid=" + teamid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Team>() {
	 
	        @Override
	        public Team extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Team team = new Team();
	                team.setteamid(rs.getInt("teamid"));
	                team.setteamname(rs.getString("teamname"));
	                team.setbranchid(rs.getInt("branchid"));
	                team.setleaderid(rs.getInt("leaderid"));
	                team.setleadername(rs.getString("leadername"));
	                return team;
	            }	 
	            return null;
	        }
        });
    }

    public Team getByName(String teamname) {
        String sql = "SELECT t.teamid AS teamid, "
        		+ "t.teamname AS teamname, "
        		+ "t.branchid AS branchid, "
        		+ "t.leaderid AS leaderid, "
        		+ "l.username AS leadername "
        		+ "FROM tblTeam t "
	    		+ "LEFT JOIN tblUser l on t.leaderid = l.userid "
	    		+ "WHERE t.teamname='" + teamname +"'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Team>() {
	 
	        @Override
	        public Team extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Team team = new Team();
	                team.setteamid(rs.getInt("teamid"));
	                team.setteamname(rs.getString("teamname"));
	                team.setbranchid(rs.getInt("branchid"));
	                team.setleaderid(rs.getInt("leaderid"));
	                team.setleadername(rs.getString("leadername"));
	                return team;
	            }	 
	            return null;
	        }
        });
    }

    public Team getByUser(int userid) {
        String sql = "SELECT t.teamid AS teamid, "
        		+ "t.teamname AS teamname, "
        		+ "t.branchid AS branchid, "
        		+ "t.leaderid AS leaderid, "
        		+ "l.username AS leadername "
        		+ "FROM tblTeam t "
	    		+ "LEFT JOIN tblUser l on t.leaderid = l.userid "
	    		+ "WHERE t.leaderid=" + userid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Team>() {
	 
	        @Override
	        public Team extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Team team = new Team();
	                team.setteamid(rs.getInt("teamid"));
	                team.setteamname(rs.getString("teamname"));
	                team.setbranchid(rs.getInt("branchid"));
	                team.setleaderid(rs.getInt("leaderid"));
	                team.setleadername(rs.getString("leadername"));
	                return team;
	            }	 
	            return null;
	        }
        });
    }
 }