package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.apache.log4j.BasicConfigurator;
import com.SpringMVC.oneapi.client.impl.SMSClient;
import com.SpringMVC.oneapi.config.Configuration;
import com.SpringMVC.oneapi.model.SMSRequest;
import com.SpringMVC.oneapi.model.SendMessageResult;

import com.SpringMVC.model.SMSLog;

import com.SpringMVC.dao.SMSLogDAO;
import com.SpringMVC.mapper.SMSLogMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class SMSLogDAOImpl extends JdbcDaoSupport implements SMSLogDAO {
 
    @Autowired
    public SMSLogDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(SMSLog smsLog) {
        String sql = "INSERT INTO tblSMSLog "
        		+ "(userid, prospectid, mobile, message, datesend, timesend) "
        		+ "VALUES (?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		smsLog.getuserid(), smsLog.getprospectid(), smsLog.getmobile(), 
        		smsLog.getmessage(), smsLog.getdatesend(), smsLog.gettimesend());
        }
        
    public void send(String recipient, String message) {
    	String USERNAME = "mspsys";
    	String PASSWORD = "MamaPapa@123";
    	String SENDER = "InfoSMS";
    	
    	BasicConfigurator.configure();    	
    	Configuration configuration = new Configuration(USERNAME, PASSWORD);
		SMSClient smsClient = new SMSClient(configuration);
		SMSRequest smsRequest = new SMSRequest(SENDER, message, recipient);
		SendMessageResult sendMessageResult = smsClient.getSMSMessagingClient().sendSMS(smsRequest);
		System.out.println(sendMessageResult);	
	}

    public List<SMSLog> list(int userid) {
        String sql = "SELECT s.userid, u.username, u.mobile AS usermobile, "
        		+ "s.prospectid, p.firstname, p.lastname, p.mobile AS mobile, "
        		+ "s.message, s.datesend, s.timesend "	    		
        		+ "FROM tblSMSLog s "
        		+ "LEFT JOIN tblUser u ON s.userid = u.userid "
        		+ "LEFT JOIN tblProspect p ON s.prospectid = p.prospectid "
        		+ "WHERE s.userid = " + userid;
        SMSLogMapper mapper = new SMSLogMapper();
        List<SMSLog> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<SMSLog> listByTeam(int teamid) {
        String sql = "SELECT s.userid, u.username, u.mobile AS usermobile, "
        		+ "s.prospectid, p.firstname, p.lastname, p.mobile AS mobile, "
        		+ "s.message, s.datesend, s.timesend "	    		
        		+ "FROM tblSMSLog s "
        		+ "LEFT JOIN tblUser u ON s.userid = u.userid "
        		+ "LEFT JOIN tblProspect p ON s.prospectid = p.prospectid "
        		+ "WHERE u.teamid = " + teamid;
        SMSLogMapper mapper = new SMSLogMapper();
        List<SMSLog> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<SMSLog> listByBranch(int branchid) {
        String sql = "SELECT s.userid, u.username, u.mobile AS usermobile, "
        		+ "s.prospectid, p.firstname, p.lastname, p.mobile AS mobile, "
        		+ "s.message, s.datesend, s.timesend "	    		
        		+ "FROM tblSMSLog s "
        		+ "LEFT JOIN tblUser u ON s.userid = u.userid "
        		+ "LEFT JOIN tblProspect p ON s.prospectid = p.prospectid "
        		+ "WHERE u.branchid = " + branchid;
        SMSLogMapper mapper = new SMSLogMapper();
        List<SMSLog> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<SMSLog> listByCompany(int companyid) {
        String sql = "SELECT s.userid, u.username, u.mobile AS usermobile, "
        		+ "s.prospectid, p.firstname, p.lastname, p.mobile AS mobile, "
        		+ "s.message, s.datesend, s.timesend "	    		
        		+ "FROM tblSMSLog s "
        		+ "LEFT JOIN tblUser u ON s.userid = u.userid "
        		+ "LEFT JOIN tblProspect p ON s.prospectid = p.prospectid "
        		+ "WHERE u.companyid = " + companyid;
        SMSLogMapper mapper = new SMSLogMapper();
        List<SMSLog> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public SMSLog get(int smsid) {
        String sql = "SELECT s.userid, u.username, u.mobile AS usermobile, "
        		+ "s.prospectid, p.firstname, p.lastname, p.mobile AS mobile, "
        		+ "s.message, s.datesend, s.timesend "	    		
        		+ "FROM tblSMSLog s "
        		+ "LEFT JOIN tblUser u ON s.userid = u.userid "
        		+ "LEFT JOIN tblProspect p ON s.prospectid = p.prospectid "
	    		+ "WHERE s.smsid=" + smsid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<SMSLog>() {
	 
	        @Override
	        public SMSLog extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	SMSLog smsLog = new SMSLog();
	            	smsLog.setsmsid(rs.getInt("smsid"));
	            	smsLog.setuserid(rs.getInt("userid"));
	            	smsLog.setusername(rs.getString("username"));
	            	smsLog.setusermobile(rs.getString("usermobile"));
	            	smsLog.setprospectid(rs.getInt("prospectid"));
	            	smsLog.setfirstname(rs.getString("firstname"));
	            	smsLog.setlastname(rs.getString("lastname"));
	            	smsLog.setmobile(rs.getString("mobile"));
	            	smsLog.setmessage(rs.getString("message"));
	            	smsLog.setdatesend(rs.getDate("datesend"));
	            	smsLog.settimesend(rs.getTime("timesend"));
	                return smsLog;
	            }	 
	            return null;
	        }
        });
    }

}