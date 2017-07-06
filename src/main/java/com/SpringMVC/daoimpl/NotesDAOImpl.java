package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Notes;
import com.SpringMVC.dao.NotesDAO;
import com.SpringMVC.mapper.NotesMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class NotesDAOImpl extends JdbcDaoSupport implements NotesDAO {
 
    @Autowired
    public NotesDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Notes notes) {
        String sql = "INSERT INTO tblNotes "
        		+ "(notedate, notetime, userid, teamid, branchid, companyid, prospectid, "
        		+ "note, status, remark, read, reviewby, reviewdate, reviewtime) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		notes.getnotedate(), notes.getnotetime(), notes.getuserid(), 
        		notes.getteamid(), notes.getbranchid(), notes.getcompanyid(), 
        		notes.getprospectid(), notes.getnote(), notes.getstatus(), 
        		notes.getremark(), notes.getread(), notes.getreviewby(), notes.getreviewdate(),
        		notes.getreviewtime());
    }
    
    public void update(Notes notes) {
        String sql = "UPDATE tblNotes "
        		+ "SET note=?, status=?, remark=?, read=?, reviewby=?, reviewdate=?, reviewtime=? "
        		+ "WHERE noteid=?";
        this.getJdbcTemplate().update(sql, notes.getnote(), notes.getstatus(), notes.getremark(), notes.getread(), 
        		notes.getreviewby(), notes.getreviewdate(), notes.getreviewtime(), notes.getnoteid());
    }
    
    public void delete(int noteid) {
        String sql = "DELETE FROM tblNotes WHERE noteid=?";
        this.getJdbcTemplate().update(sql, noteid);
    }
    
    public List<Notes> listByProspect(int prospectid) {
	    String sql = "SELECT noteid, notedate, notetime, "
	    		+ "n.userid, u.username, "
	    		+ "n.teamid, t.teamname, "
	    		+ "n.branchid, b.branchname, "
	    		+ "n.companyid, c.companyname, "
        		+ "n.prospectid, p.firstname AS prospectname, "
        		+ "note, n.status, n.remark, n.read, reviewby, rb.username AS reviewbyname, "
        		+ "reviewdate, reviewtime "
        		+ "FROM tblNotes n "
        		+ "LEFT JOIN tblUser u on u.userid = n.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = n.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = n.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = n.companyid "
        		+ "LEFT JOIN tblProspect p on p.prospectid = n.prospectid "
        		+ "LEFT JOIN tblUser rb on rb.userid = n.reviewby "
        		+ "WHERE n.prospectid = " + prospectid;
	    NotesMapper mapper = new NotesMapper();
        List<Notes> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Notes> list(int userid) {
	    String sql = "SELECT noteid, notedate, notetime, "
	    		+ "n.userid, u.username, "
	    		+ "n.teamid, t.teamname, "
	    		+ "n.branchid, b.branchname, "
	    		+ "n.companyid, c.companyname, "
        		+ "n.prospectid, p.firstname AS prospectname, "
        		+ "note, n.status, n.remark, n.read, reviewby, rb.username AS reviewbyname, "
        		+ "reviewdate, reviewtime "
        		+ "FROM tblNotes n "
        		+ "LEFT JOIN tblUser u on u.userid = n.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = n.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = n.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = n.companyid "
        		+ "LEFT JOIN tblProspect p on p.prospectid = n.prospectid "
        		+ "LEFT JOIN tblUser rb on rb.userid = n.reviewby "
        		+ "WHERE n.userid = " + userid;
	    NotesMapper mapper = new NotesMapper();
        List<Notes> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Notes> listByTeam(int teamid) {
	    String sql = "SELECT noteid, notedate, notetime, "
	    		+ "n.userid, u.username, "
	    		+ "n.teamid, t.teamname, "
	    		+ "n.branchid, b.branchname, "
	    		+ "n.companyid, c.companyname, "
        		+ "n.prospectid, p.firstname AS prospectname, "
        		+ "note, n.status, n.remark, n.read, reviewby, rb.username AS reviewbyname, "
        		+ "reviewdate, reviewtime "
        		+ "FROM tblNotes n "
        		+ "LEFT JOIN tblUser u on u.userid = n.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = n.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = n.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = n.companyid "
        		+ "LEFT JOIN tblProspect p on p.prospectid = n.prospectid "
        		+ "LEFT JOIN tblUser rb on rb.userid = n.reviewby "
        		+ "WHERE t.teamid = " + teamid;
	    NotesMapper mapper = new NotesMapper();
        List<Notes> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Notes> listByBranch(int branchid) {
	    String sql = "SELECT noteid, notedate, notetime, "
	    		+ "n.userid, u.username, "
	    		+ "n.teamid, t.teamname, "
	    		+ "n.branchid, b.branchname, "
	    		+ "n.companyid, c.companyname, "
        		+ "n.prospectid, p.firstname AS prospectname, "
        		+ "note, n.status, n.remark, n.read, reviewby, rb.username AS reviewbyname, "
        		+ "reviewdate, reviewtime "
        		+ "FROM tblNotes n "
        		+ "LEFT JOIN tblUser u on u.userid = n.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = n.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = n.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = n.companyid "
        		+ "LEFT JOIN tblProspect p on p.prospectid = n.prospectid "
        		+ "LEFT JOIN tblUser rb on rb.userid = n.reviewby "
        		+ "WHERE b.branchid = " + branchid;
	    NotesMapper mapper = new NotesMapper();
        List<Notes> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Notes> listByCompany(int companyid) {
	    String sql = "SELECT noteid, notedate, notetime, "
	    		+ "n.userid, u.username, "
	    		+ "n.teamid, t.teamname, "
	    		+ "n.branchid, b.branchname, "
	    		+ "n.companyid, c.companyname, "
        		+ "n.prospectid, p.firstname AS prospectname, "
        		+ "note, n.status, n.remark, n.read, reviewby, rb.username AS reviewbyname, "
        		+ "reviewdate, reviewtime "
        		+ "FROM tblNotes n "
        		+ "LEFT JOIN tblUser u on u.userid = n.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = n.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = n.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = n.companyid "
        		+ "LEFT JOIN tblProspect p on p.prospectid = n.prospectid "
        		+ "LEFT JOIN tblUser rb on rb.userid = n.reviewby "
        		+ "WHERE c.companyid = " + companyid;
	    NotesMapper mapper = new NotesMapper();
        List<Notes> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Notes get(int noteid) {
	    String sql = "SELECT noteid,  notedate, notetime, "
	    		+ "n.userid, u.username, "
	    		+ "n.teamid, t.teamname, "
	    		+ "n.branchid, b.branchname, "
	    		+ "n.companyid, c.companyname, "
        		+ "n.prospectid, p.firstname AS prospectname, "
        		+ "note, n.status, n.remark, n.read, reviewby, rb.username AS reviewbyname, "
        		+ "reviewdate, reviewtime "
        		+ "FROM tblNotes n "
        		+ "LEFT JOIN tblUser u on u.userid = n.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = n.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = n.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = n.companyid "
        		+ "LEFT JOIN tblProspect p on p.prospectid = n.prospectid "
        		+ "LEFT JOIN tblUser rb on rb.userid = n.reviewby "
	    		+ "WHERE n.noteid=" + noteid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Notes>() {
	 
	        @Override
	        public Notes extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Notes notes = new Notes();
	                notes.setnoteid(rs.getInt("noteid"));
	                notes.setnotedate(rs.getDate("notedate"));
	                notes.setnotetime(rs.getTime("notetime"));
	                notes.setuserid(rs.getInt("userid"));
	                notes.setusername(rs.getString("username"));
	                notes.setteamid(rs.getInt("teamid"));
	                notes.setteamname(rs.getString("teamname"));
	                notes.setbranchid(rs.getInt("branchid"));
	                notes.setbranchname(rs.getString("branchname"));
	                notes.setcompanyid(rs.getInt("companyid"));
	                notes.setcompanyname(rs.getString("companyname"));
	                notes.setprospectid(rs.getInt("prospectid"));
	                notes.setprospectname(rs.getString("prospectname"));
	                notes.setnote(rs.getString("note"));
	                notes.setstatus(rs.getInt("status"));
	                notes.setremark(rs.getString("remark"));
	                notes.setread(rs.getBoolean("read"));
	                notes.setreviewby(rs.getInt("reviewby"));
	                notes.setreviewbyname(rs.getString("reviewbyname"));
	                notes.setreviewdate(rs.getDate("reviewdate"));
	                notes.setreviewtime(rs.getTime("reviewtime"));
	                return notes;
	            }	 
	            return null;
	        }
        });
    }
}