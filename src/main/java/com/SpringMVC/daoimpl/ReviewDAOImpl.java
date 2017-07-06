package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Review;
import com.SpringMVC.dao.ReviewDAO;
import com.SpringMVC.mapper.ReviewMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ReviewDAOImpl extends JdbcDaoSupport implements ReviewDAO {
 
    @Autowired
    public ReviewDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Review review) {
        String sql = "INSERT INTO tblReview "
        		+ "(userid, period, teamid, branchid, companyid, targetid, teamtargetid, reviewdate, "
        		+ "prospect, testdrive, closed, minute, reviewby) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		review.getuserid(), review.getperiod(), review.getteamid(), review.getbranchid(), review.getcompanyid(), 
        		review.gettargetid(), review.getteamtargetid(), review.getreviewdate(), 
        		review.getprospect(), review.gettestdrive(), review.getclosed(), 
        		review.getminute(), review.getreviewby());
    }
    
    public void update(Review review) {
        String sql = "UPDATE tblReview SET reviewdate=?, prospect=?, testdrive=?, closed=?, minute=?, reviewby=?"
        		+ "WHERE reviewid=?";
        this.getJdbcTemplate().update(sql, review.getreviewdate(), 
        		review.getprospect(), review.gettestdrive(), review.getclosed(), 
        		review.getminute(), review.getreviewby(), 
        		review.getreviewid());
    }
    
    public void delete(int reviewid) {
        String sql = "DELETE FROM tblReview WHERE reviewid=?";
        this.getJdbcTemplate().update(sql, reviewid);
    }
    
    public List<Review> list(int userid) {
	    String sql = "SELECT reviewid, period, "
	    		+ "r.userid, u.username, "
	    		+ "r.teamid, t.teamname, "
	    		+ "r.branchid, b.branchname, "
	    		+ "r.companyid, c.companyname, "
	    		+ "targetid, teamtargetid, reviewdate, "
        		+ "prospect, testdrive, closed, "
        		+ "minute, reviewby, rb.username AS reviewbyname "
        		+ "FROM tblReview r "
        		+ "LEFT JOIN tblUser u on u.userid = r.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = r.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = r.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = r.companyid "
        		+ "LEFT JOIN tblUser rb on rb.userid = r.reviewby "
        		+ "WHERE r.userid = " + userid;
	    ReviewMapper mapper = new ReviewMapper();
        List<Review> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Review> listByTeam(int teamid) {
	    String sql = "SELECT reviewid, period, "
	    		+ "r.userid, u.username, "
	    		+ "r.teamid, t.teamname, "
	    		+ "r.branchid, b.branchname, "
	    		+ "r.companyid, c.companyname, "
	    		+ "targetid, teamtargetid, reviewdate, "
        		+ "prospect, testdrive, closed, "
        		+ "minute, reviewby, rb.username AS reviewbyname "
        		+ "FROM tblReview r "
        		+ "LEFT JOIN tblUser u on u.userid = r.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = r.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = r.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = r.companyid "
        		+ "LEFT JOIN tblUser rb on rb.userid = r.reviewby "
        		+ "WHERE t.teamid = " + teamid;
	    ReviewMapper mapper = new ReviewMapper();
        List<Review> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Review> listByBranch(int branchid) {
	    String sql = "SELECT reviewid, period, "
	    		+ "r.userid, u.username, "
	    		+ "r.teamid, t.teamname, "
	    		+ "r.branchid, b.branchname, "
	    		+ "r.companyid, c.companyname, "
	    		+ "targetid, teamtargetid, reviewdate, "
        		+ "prospect, testdrive, closed, "
        		+ "minute, reviewby, rb.username AS reviewbyname "
        		+ "FROM tblReview r "
        		+ "LEFT JOIN tblUser u on u.userid = r.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = r.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = r.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = r.companyid "
        		+ "LEFT JOIN tblUser rb on rb.userid = r.reviewby "
        		+ "WHERE b.branchid = " + branchid;
	    ReviewMapper mapper = new ReviewMapper();
        List<Review> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Review> listByCompany(int companyid) {
	    String sql = "SELECT reviewid, period, "
	    		+ "r.userid, u.username, "
	    		+ "r.teamid, t.teamname, "
	    		+ "r.branchid, b.branchname, "
	    		+ "r.companyid, c.companyname, "
	    		+ "targetid, teamtargetid, reviewdate, "
        		+ "prospect, testdrive, closed, "
        		+ "minute, reviewby, rb.username AS reviewbyname "
        		+ "FROM tblReview r "
        		+ "LEFT JOIN tblUser u on u.userid = r.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = r.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = r.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = r.companyid "
        		+ "LEFT JOIN tblUser rb on rb.userid = r.reviewby "
        		+ "WHERE c.companyid = " + companyid;
	    ReviewMapper mapper = new ReviewMapper();
        List<Review> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Review get(int reviewid) {
	    String sql = "SELECT reviewid, period, "
	    		+ "r.userid, u.username, "
	    		+ "r.teamid, t.teamname, "
	    		+ "r.branchid, b.branchname, "
	    		+ "r.companyid, c.companyname, "
	    		+ "targetid, teamtargetid, reviewdate, "
        		+ "prospect, testdrive, closed, "
        		+ "minute, reviewby, rb.username AS reviewbyname "
        		+ "FROM tblReview r "
        		+ "LEFT JOIN tblUser u on u.userid = r.userid "
        		+ "LEFT JOIN tblTeam t on t.teamid = r.teamid "
        		+ "LEFT JOIN tblBranch b on b.branchid = r.branchid "
        		+ "LEFT JOIN tblCompany c on c.companyid = r.companyid "
        		+ "LEFT JOIN tblUser rb on rb.userid = r.reviewby "
	    		+ "WHERE r.reviewid=" + reviewid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Review>() {
	 
	        @Override
	        public Review extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Review review = new Review();
	                review.setreviewid(rs.getInt("reviewid"));
	                review.setperiod(rs.getString("period"));
	                review.setuserid(rs.getInt("userid"));
	                review.setusername(rs.getString("username"));
	                review.setteamid(rs.getInt("teamid"));
	                review.setteamname(rs.getString("teamname"));
	                review.setbranchid(rs.getInt("branchid"));
	                review.setbranchname(rs.getString("branchname"));
	                review.setcompanyid(rs.getInt("companyid"));
	                review.setcompanyname(rs.getString("companyname"));
	                review.settargetid(rs.getInt("targetid"));
	                review.setteamtargetid(rs.getInt("teamtargetid"));
	                review.setreviewdate(rs.getDate("reviewdate"));
	                review.setprospect(rs.getInt("prospect"));
	                review.settestdrive(rs.getInt("testdrive"));
	                review.setclosed(rs.getInt("closed"));
	                review.setminute(rs.getString("minute"));
	                review.setreviewby(rs.getInt("reviewby"));
	                review.setreviewbyname(rs.getString("reviewbyname"));
	                return review;
	            }	 
	            return null;
	        }
        });
    }
}