package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import com.SpringMVC.model.Event;

import com.SpringMVC.dao.EventDAO;
import com.SpringMVC.mapper.EventMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class EventDAOImpl extends JdbcDaoSupport implements EventDAO {
 
    @Autowired
    public EventDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Event event) {
        String sql = "INSERT INTO tblEvent "
        		+ "(userid, prospectid, activityid, "
        		+ "title, remark, period, "
        		+ "startdate, starttime, enddate, endtime, "
        		+ "url, allDay, status) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		event.getuserid(), event.getprospectid(), event.getactivityid(), 
        		event.gettitle(), event.getremark(), event.getperiod(),
        		event.getstartdate(), event.getstarttime(), event.getenddate(), event.getendtime(), 
        		event.geturl(), event.getallDay(), event.getstatus());
        }
    
    public void update(Event event) {
        String sql = "UPDATE tblEvent "
        		+ "SET title=?, remark=?, period=?, startdate=?, starttime=?, "
        		+ "enddate=?, endtime=?, url=?, allDay=?, status=? "
        		+ "WHERE id=?";
        this.getJdbcTemplate().update(sql, event.gettitle(), event.getremark(), event.getperiod(),
        		event.getstartdate(), event.getstarttime(), event.getenddate(), event.getendtime(), 
        		event.geturl(), event.getallDay(), event.getstatus(), event.getid());
    }

    public void delete(int id) {
        String sql = "DELETE FROM tblEvent WHERE id=?";
        this.getJdbcTemplate().update(sql, id);
    }
    
    public List<Event> list(int userid) {
        String sql = "SELECT e.id, e.userid, e.prospectid, p.firstname, p.lastname, p.mobile, "
        		+ "e.activityid, b.brandname, m.modelname, a.quotationpdflink, e.title, e.remark, e.period, "
        		+ "e.startdate, e.starttime, e.enddate, e.endtime, e.url, e.allDay, e.status "	    		
        		+ "FROM tblEvent e "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = e.prospectid "
        		+ "LEFT JOIN tblActivity a ON a.activityid = e.activityid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE e.userid = " + userid;
        EventMapper mapper = new EventMapper();
        List<Event> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Event> listByTeam(int teamid) {
        String sql = "SELECT e.id, e.userid, e.prospectid, p.firstname, p.lastname, p.mobile, "
        		+ "e.activityid, b.brandname, m.modelname, a.quotationpdflink, e.title, e.remark, e.period, "
        		+ "e.startdate, e.starttime, e.enddate, e.endtime, e.url, e.allDay, e.status "	    		
        		+ "FROM tblEvent e "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = e.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = e.userid "
        		+ "LEFT JOIN tblActivity a ON a.activityid = e.activityid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE u.teamid = " + teamid;
        EventMapper mapper = new EventMapper();
        List<Event> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Event> listByBranch(int branchid) {
        String sql = "SELECT e.id, e.userid, e.prospectid, p.firstname, p.lastname, p.mobile, "
        		+ "e.activityid, b.brandname, m.modelname, a.quotationpdflink, e.title, e.remark, e.period, "
        		+ "e.startdate, e.starttime, e.enddate, e.endtime, e.url, e.allDay, e.status "	    		
        		+ "FROM tblEvent e "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = e.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = e.userid "
        		+ "LEFT JOIN tblActivity a ON a.activityid = e.activityid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE u.branchid = " + branchid;
        EventMapper mapper = new EventMapper();
        List<Event> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Event> listByCompany(int companyid) {
        String sql = "SELECT e.id, e.userid, e.prospectid, p.firstname, p.lastname, p.mobile, "
        		+ "e.activityid, b.brandname, m.modelname, a.quotationpdflink, e.title, e.remark, e.period, "
        		+ "e.startdate, e.starttime, e.enddate, e.endtime, e.url, e.allDay, e.status "	    		
        		+ "FROM tblEvent e "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = e.prospectid "
        		+ "LEFT JOIN tblUser u ON u.userid = e.userid "
        		+ "LEFT JOIN tblActivity a ON a.activityid = e.activityid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
        		+ "WHERE u.companyid = " + companyid;
        EventMapper mapper = new EventMapper();
        List<Event> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Event get(int eventid) {
        String sql = "SELECT e.id, e.userid, e.prospectid, p.firstname, p.lastname, p.mobile, "
        		+ "e.activityid, b.brandname, m.modelname, a.quotationpdflink, e.title, e.remark, e.period, "
        		+ "e.startdate, e.starttime, e.enddate, e.endtime, e.url, e.allDay, e.status "	    		
        		+ "FROM tblEvent e "
        		+ "LEFT JOIN tblProspect p ON p.prospectid = e.prospectid "
        		+ "LEFT JOIN tblActivity a ON a.activityid = e.activityid "
        		+ "LEFT JOIN tblBrand b ON b.brandid = a.brandid "
        		+ "LEFT JOIN tblModel m ON m.modelid = a.modelid "
	    		+ "WHERE e.id=" + eventid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Event>() {
	 
	        @Override
	        public Event extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Event event = new Event();
	            	event.setid(rs.getInt("id"));
	            	event.setuserid(rs.getInt("userid"));
	            	event.setprospectid(rs.getInt("prospectid"));
	            	event.setfirstname(rs.getString("firstname"));
	            	event.setlastname(rs.getString("lastname"));
	            	event.setmobile(rs.getString("mobile"));
	            	event.setactivityid(rs.getInt("activityid"));
	            	event.setbrandname(rs.getString("brandname"));
	            	event.setmodelname(rs.getString("modelname"));
	            	event.setquotationpdflink(rs.getString("quotationpdflink"));
	            	event.settitle(rs.getString("title"));
	            	event.setremark(rs.getString("remark"));
	            	event.setperiod(rs.getString("period"));
	            	event.setstartdate(rs.getDate("startdate"));
	            	event.setstarttime(rs.getTime("starttime"));
	            	if (rs.getDate("startdate")==null){
	            		event.setstartdatetime("");
	            	} else {
		            	event.setstartdatetime(rs.getDate("startdate").toString()+" "+rs.getTime("starttime").toString());	            		
	            	}
	            	event.setenddate(rs.getDate("enddate"));
	            	event.setendtime(rs.getTime("endtime"));
	            	if (rs.getDate("enddate")==null){
	            		event.setenddatetime("");
	            	} else {
	            		event.setenddatetime(rs.getDate("enddate").toString()+" "+rs.getTime("endtime").toString());
	            	}
	            	event.seturl(rs.getString("url"));
	            	event.setallDay(rs.getBoolean("allDay"));
	            	event.setstatus(rs.getBoolean("status"));
	                return event;
	            }	 
	            return null;
	        }
        });
    }

}