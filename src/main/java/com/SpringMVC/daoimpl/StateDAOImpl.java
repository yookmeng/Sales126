package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource; 
import com.SpringMVC.dao.StateDAO;
import com.SpringMVC.mapper.StateMapper;
import com.SpringMVC.model.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class StateDAOImpl extends JdbcDaoSupport implements StateDAO {
 
    @Autowired
    public StateDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(State state) {
        String sql = "INSERT INTO tblState (countryid, statename) VALUES (?, ?)";
        this.getJdbcTemplate().update(sql, 
        		state.getcountryid(), state.getstatename());
    }
    
    public void update(State state) {
        String sql = "UPDATE tblState SET statename=? WHERE stateid=?";
        this.getJdbcTemplate().update(sql, state.getstatename(), state.getstateid());
    }
    
    public void delete(int stateid) {
        String sql = "DELETE FROM tblState WHERE stateid=?";
        this.getJdbcTemplate().update(sql, stateid);
    }
    
    public State get(int stateid) {
	    String sql = "SELECT s.stateid, s.countryid, c.countryname, s.statename "
	    		   + "FROM tblState s "
	    		   + "LEFT JOIN tblCountry c ON c.countryid = s.countryid "
	    		   + "WHERE s.stateid="+stateid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<State>() {
	 
			@Override
	        public State extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	State state = new State();
	            	state.setstateid(rs.getInt("stateid"));
	            	state.setcountryid(rs.getInt("countryid"));
	            	state.setcountryname(rs.getString("countryname"));
	            	state.setstatename(rs.getString("statename"));
	                return state;
	            }	 
	            return null;
	        }
        });
    }
    
    public List<State> list() {
	    String sql = "SELECT s.stateid, s.countryid, c.countryname, s.statename "
	    		   + "FROM tblState s "
	    		   + "LEFT JOIN tblCountry c ON c.countryid = s.countryid ";
        StateMapper mapper = new StateMapper();
        List<State> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }                    
}