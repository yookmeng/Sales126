package com.SpringMVC.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import com.SpringMVC.model.Closed;
import com.SpringMVC.dao.ClosedDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ClosedDAOImpl extends JdbcDaoSupport implements ClosedDAO {
 
    @Autowired
    public ClosedDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Closed closed) {
		Connection conn = this.getConnection();
		try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spClosedInsUpd(?, ?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "0");
	    	proc.setInt(3, closed.getclosedid());
	    	proc.setDate(4, closed.getclosedate());
	    	proc.setInt(5, closed.getprospectid());
	    	proc.setInt(6, closed.getactivityid());
	    	proc.setFloat(7, closed.getdownpayment());
	    	proc.setString(8, closed.getremark());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public void update(Closed closed) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spClosedInsUpd(?, ?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setString(2, "1");
	    	proc.setInt(3, closed.getclosedid());
	    	proc.setDate(4, closed.getclosedate());
	    	proc.setInt(5, closed.getprospectid());
	    	proc.setInt(6, closed.getactivityid());
	    	proc.setFloat(7, closed.getdownpayment());
	    	proc.setString(8, closed.getremark());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }

    public void delete(int closedid) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spClosedDel(?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, closedid);

	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }
    
    public Closed get(int closedid) {
	    String sql = "SELECT closedid, closedate, prospectid, activityid, downpayment, remark "
	    		+ "FROM tblClosed "
	    		+ "WHERE closedid=" + closedid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Closed>() {
	 
			@Override
	        public Closed extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Closed closed = new Closed();
	            	closed.setclosedid(rs.getInt("closedid"));
	            	closed.setclosedate(rs.getDate("closedate"));
	            	closed.setprospectid(rs.getInt("prospectid"));
	            	closed.setactivityid(rs.getInt("activityid"));
	            	closed.setdownpayment(rs.getFloat("downpayment"));
	            	closed.setremark(rs.getString("remark"));
	                return closed;
	            }	 
	            return null;
	        }
        });
    }
}