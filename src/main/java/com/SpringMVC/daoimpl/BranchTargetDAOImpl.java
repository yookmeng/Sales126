package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.BranchTarget;
import com.SpringMVC.dao.BranchTargetDAO;
import com.SpringMVC.mapper.BranchTargetMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class BranchTargetDAOImpl extends JdbcDaoSupport implements BranchTargetDAO {
 
    @Autowired
    public BranchTargetDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(BranchTarget branchTarget) {
        String sql = "INSERT INTO tblBranchTarget "
        		+ "(branchid, period, companytargetid, prospect, testdrive, closed) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		branchTarget.getbranchid(), branchTarget.getperiod(), branchTarget.getcompanytargetid(), 
        		branchTarget.getprospect(), branchTarget.gettestdrive(), branchTarget.getclosed());
    }
    
    public void update(BranchTarget branchTarget) {
        String sql = "UPDATE tblBranchTarget SET prospect=?, testdrive=?, closed=? "
        		+ "WHERE targetid=?";
        this.getJdbcTemplate().update(sql, 
        		branchTarget.getprospect(), branchTarget.gettestdrive(), branchTarget.getclosed(), 
        		branchTarget.gettargetid());
    }
    
    public void delete(int targetid) {
        String sql = "DELETE FROM tblBranchTarget WHERE targetid=?";
        this.getJdbcTemplate().update(sql, targetid);
    }
    
    public BranchTarget get(int targetid) {
	    String sql = "SELECT bt.targetid AS targetid, "
	    		+ "bt.branchid AS branchid, "
	    		+ "b.branchname AS branchname, "
        		+ "bt.period AS period, "
        		+ "bt.companytargetid AS companytargetid, "
        		+ "bt.prospect AS prospect, "
        		+ "bt.testdrive AS testdrive, "
        		+ "bt.closed AS closed "
	    		+ "FROM tblBranchTarget bt "
        		+ "LEFT JOIN tblBranch b ON b.branchid = bt.branchid "
	    		+ "WHERE targetid="+targetid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<BranchTarget>() {
	 
	        @Override
	        public BranchTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                BranchTarget branchTarget = new BranchTarget();
	                branchTarget.settargetid(rs.getInt("targetid"));
	                branchTarget.setbranchid(rs.getInt("branchid"));
	                branchTarget.setbranchname(rs.getString("branchname"));
	                branchTarget.setcompanytargetid(rs.getInt("companytargetid"));
	                branchTarget.setperiod(rs.getString("period"));
	                branchTarget.setprospect(rs.getInt("prospect"));
	                branchTarget.settestdrive(rs.getInt("testdrive"));
	                branchTarget.setclosed(rs.getInt("closed"));
	                return branchTarget;
	            }	 
	            return null;
	        }
        });
    }

    public BranchTarget getByPeriod(String period, int branchid) {
	    String sql = "SELECT targetid, bt.branchid, b.branchname, period, "
        		+ "companytargetid, prospect, testdrive, closed "
	    		+ "FROM tblBranchTarget bt "
        		+ "LEFT JOIN tblBranch b ON b.branchid = bt.branchid "
	    		+ "WHERE period = '" + period + "' "
	    		+ "AND bt.branchid=" + branchid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<BranchTarget>() {
	        @Override
	        public BranchTarget extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                BranchTarget branchTarget = new BranchTarget();
	                branchTarget.settargetid(rs.getInt("targetid"));
	                branchTarget.setbranchid(rs.getInt("branchid"));
	                branchTarget.setbranchname(rs.getString("branchname"));
	                branchTarget.setcompanytargetid(rs.getInt("companytargetid"));
	                branchTarget.setperiod(rs.getString("period"));
	                branchTarget.setprospect(rs.getInt("prospect"));
	                branchTarget.settestdrive(rs.getInt("testdrive"));
	                branchTarget.setclosed(rs.getInt("closed"));
	                return branchTarget;
	            }	 
	            return null;
	        }
        });
    }

    public List<BranchTarget> list(String period, int companyid) {
        String sql = "SELECT bt.targetid AS targetid, "
        		+ "bt.branchid AS branchid, "
        		+ "b.branchname AS branchname, "
        		+ "bt.period AS period, "
        		+ "bt.companytargetid AS companytargetid, "
        		+ "bt.prospect AS prospect, "
        		+ "bt.testdrive AS testdrive, "
        		+ "bt.closed AS closed "
        		+ "FROM tblBranchTarget bt "
        		+ "LEFT JOIN tblBranch b ON b.branchid = bt.branchid "
        		+ "WHERE bt.period = '" + period + "' "
				+ "AND b.companyid = " + companyid + " "
				+ "ORDER BY branchid";
        BranchTargetMapper mapper = new BranchTargetMapper();
        List<BranchTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<BranchTarget> listByCompany(int companyid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
        String sql = "SELECT bt.targetid AS targetid, "
        		+ "bt.branchid AS branchid, "
        		+ "b.branchname AS branchname, "
        		+ "bt.period AS period, "
        		+ "bt.companytargetid AS companytargetid, "
        		+ "bt.prospect AS prospect, "
        		+ "bt.testdrive AS testdrive, "
        		+ "bt.closed AS closed "
        		+ "FROM tblBranchTarget bt "
        		+ "LEFT JOIN tblBranch b ON b.branchid = bt.branchid "
        		+ "WHERE b.companyid = " + companyid + " "
				+ "AND bt.period>='" + period + "' "
        		+ "ORDER BY period";
        BranchTargetMapper mapper = new BranchTargetMapper();
        List<BranchTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<BranchTarget> listByBranch(int branchid) {
    	String period = "";
    	int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        int currentmonth = Calendar.getInstance().get(Calendar.MONTH)+1;
        currentmonth = currentmonth - 3;
        if (currentmonth < 1){
        	period = String.valueOf(currentyear-1)+"-"+String.valueOf(currentmonth+12);
        } else {
        	period = String.valueOf(currentyear)+"-"+String.valueOf(currentmonth);
        } 
        String sql = "SELECT bt.targetid AS targetid, "
        		+ "bt.branchid AS branchid, "
        		+ "b.branchname AS branchname, "
        		+ "bt.period AS period, "
        		+ "bt.companytargetid AS companytargetid, "
        		+ "bt.prospect AS prospect, "
        		+ "bt.testdrive AS testdrive, "
        		+ "bt.closed AS closed "
        		+ "FROM tblBranchTarget bt "
        		+ "LEFT JOIN tblBranch b ON b.branchid = bt.branchid "
        		+ "WHERE bt.branchid = " + branchid + " "
 				+ "AND bt.period>='" + period + "' "        		
        		+ "ORDER BY period";
        BranchTargetMapper mapper = new BranchTargetMapper();
        List<BranchTarget> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
}