package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.mapper.UserLoginMapper;
import com.SpringMVC.model.UserLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
@Transactional
public class UserLoginDAOImpl extends JdbcDaoSupport implements UserLoginDAO {
	@Autowired
    public UserLoginDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
  
    @Override
    public String getUserRoles(String username) {
        String sql = "Select role from tblRole where username = ? ";

        String role = (String)getJdbcTemplate().queryForObject(sql, new Object[] {username}, String.class);         
        return role;
    }
     
    @Override
    public List<String> getAllRoles() {
        String sql = "Select codeid AS role from tblCodeMaster where codetype = 'ROLE' and codeid <> 'DEV'";
         
        List<String> roles = this.getJdbcTemplate().queryForList(sql, String.class);         
        return roles;
    }
    
    public void save(UserLogin userLogin) {
    	String sqlUser = "INSERT INTO tblUser ("
    			+ "username, password, teamid, branchid, companyid, mobile, email, imgurl, imgthumburl, enabled) "
    			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, '1')";
        this.getJdbcTemplate().update(sqlUser, 
        		userLogin.getusername(), userLogin.getpassword(), userLogin.getteamid(),
        		userLogin.getbranchid(), userLogin.getcompanyid(), userLogin.getmobile(),
        		userLogin.getemail(), userLogin.getimgurl(), userLogin.getimgthumburl());

		String sqlRole = "INSERT INTO tblRole (username, role) "
				+ "VALUES (?, ?)";
        this.getJdbcTemplate().update(sqlRole, 
        		userLogin.getusername(), userLogin.getrole());            	
    }
    
    public void update(UserLogin userLogin) {
    	String sqlUser = "UPDATE tblUser SET password=?, teamid=?, branchid=?, companyid=?, "
    			+ "mobile=?, email=?, imgurl=?, imgthumburl=? "
    			+ "WHERE userid=?";
        this.getJdbcTemplate().update(sqlUser, 
        		userLogin.getpassword(), userLogin.getteamid(), userLogin.getbranchid(), 
        		userLogin.getcompanyid(), userLogin.getmobile(), userLogin.getemail(), 
        		userLogin.getimgurl(), userLogin.getimgthumburl(), userLogin.getuserid());
    	
		String sqlRole = "UPDATE tblRole SET role=? WHERE username=?";
        this.getJdbcTemplate().update(sqlRole, 
        		userLogin.getrole(), userLogin.getusername());            
    }

    public void delete(String username) {
        String sql = "DELETE FROM tblUser WHERE username=?";
        this.getJdbcTemplate().update(sql, username);

        String sqlRole = "DELETE FROM tblRole WHERE username=?";
        this.getJdbcTemplate().update(sqlRole, username);
    }
    
    @Override
    public List<UserLogin> list(String role, int companyid) {
    	String sql = "";
    	if (role.equals("DEV") ){
            sql = "SELECT u.userid AS userid, "
        		+ "u.username, password, role, "
        		+ "u.teamid, t.teamname, "
        		+ "u.branchid, b.branchname, "
        		+ "u.companyid, c.companyname, "
        		+ "mobile, u.email, imgurl, imgthumburl "
        		+ "FROM	tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblTeam t ON u.teamid = t.teamid "
				+ "LEFT JOIN tblBranch b ON u.branchid = b.branchid "
				+ "LEFT JOIN tblCompany c ON u.companyid = c.companyid "
				+ "WHERE r.role IN ('SA', 'MD')";    		    		
    	}
    	else {
            sql = "SELECT u.userid AS userid, "
        		+ "u.username, password, role, "
        		+ "u.teamid, t.teamname, "
        		+ "u.branchid, b.branchname, "
        		+ "u.companyid, c.companyname, "
        		+ "mobile, u.email, imgurl, imgthumburl "
        		+ "FROM	tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblTeam t ON u.teamid = t.teamid "
				+ "LEFT JOIN tblBranch b ON u.branchid = b.branchid "
				+ "LEFT JOIN tblCompany c ON u.companyid = c.companyid "
        		+ "WHERE r.role IN ('MA', 'TL') "
        		+ "AND u.companyid = " + companyid;
    	}
        UserLoginMapper mapper = new UserLoginMapper();
        List<UserLogin> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    @Override
    public List<UserLogin> listByTeam(int teamid) {
    	String sql = "SELECT u.userid AS userid, "
        		+ "u.username, password, role, "
        		+ "u.teamid, t.teamname, "
        		+ "u.branchid, b.branchname, "
        		+ "u.companyid, c.companyname, "
        		+ "mobile, u.email, imgurl, imgthumburl "
        		+ "FROM	tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblTeam t ON u.teamid = t.teamid "
				+ "LEFT JOIN tblBranch b ON u.branchid = b.branchid "
				+ "LEFT JOIN tblCompany c ON u.companyid = c.companyid "
        		+ "WHERE r.role = 'USER' "
        		+ "AND u.teamid = " + teamid;
        UserLoginMapper mapper = new UserLoginMapper();
        List<UserLogin> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    @Override
    public List<String> salist() {
        String sql = "SELECT u.username AS username FROM tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
        		+ "WHERE r.role = 'SA'";
        List<String> salist = this.getJdbcTemplate().queryForList(sql, String.class);         
        return salist;
    }

    @Override
    public List<String> mdlist() {
        String sql = "SELECT u.username AS username FROM tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
        		+ "WHERE r.role = 'MD'";
        List<String> mdlist = this.getJdbcTemplate().queryForList(sql, String.class);         
        return mdlist;
    }

    @Override
    public List<String> malist() {
        String sql = "SELECT u.username AS username FROM tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
        		+ "WHERE r.role = 'MA'";
        List<String> malist = this.getJdbcTemplate().queryForList(sql, String.class);         
        return malist;
    }

    @Override
    public List<String> leaderlist() {
        String sql = "SELECT u.username AS username FROM tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
        		+ "WHERE r.role = 'TL'";
        List<String> leaderlist = this.getJdbcTemplate().queryForList(sql, String.class);         
        return leaderlist;
    }

    @Override
    public List<String> userlist(int teamid) {
        String sql = "SELECT u.username AS username FROM tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
        		+ "WHERE r.role = 'USER' "
        		+ "AND u.teamid = "+teamid;
        List<String> leaderlist = this.getJdbcTemplate().queryForList(sql, String.class);         
        return leaderlist;
    }

    @Override
    public UserLogin findUser(int userid) {
        String sql = "SELECT u.userid AS userid, "
	    		+ "u.username, password, role, "
        		+ "u.teamid, t.teamname, "
        		+ "u.branchid, b.branchname, "
        		+ "u.companyid, c.companyname, "
	    		+ "mobile, u.email, imgurl, imgthumburl "
	    		+ "FROM	tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblTeam t ON u.teamid = t.teamid "
				+ "LEFT JOIN tblBranch b ON u.branchid = b.branchid "
				+ "LEFT JOIN tblCompany c ON u.companyid = c.companyid "
				+ "WHERE u.userid=" + userid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserLogin>() {
	 
	        @Override
	        public UserLogin extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserLogin userLogin = new UserLogin();
	                userLogin.setuserid(rs.getInt("userid"));
	                userLogin.setusername(rs.getString("username"));
	                userLogin.setpassword(rs.getString("password"));	                
	                userLogin.setrole(rs.getString("role"));
	                userLogin.setteamid(rs.getInt("teamid"));
	                userLogin.setteamname(rs.getString("teamname"));
	                userLogin.setbranchid(rs.getInt("branchid"));
	                userLogin.setbranchname(rs.getString("branchname"));
	                userLogin.setcompanyid(rs.getInt("companyid"));
	                userLogin.setcompanyname(rs.getString("companyname"));
	                userLogin.setmobile(rs.getString("mobile"));
	                userLogin.setemail(rs.getString("email"));
	                userLogin.setimgurl(rs.getString("imgurl"));
	                userLogin.setimgthumburl(rs.getString("imgthumburl"));
	                return userLogin;
	            }	 
	            return null;
	        }
        });
    }

    @Override
    public UserLogin findUserEmail(String email) {
        String sql = "SELECT u.userid AS userid, "
	    		+ "u.username, password, role, "
        		+ "u.teamid, t.teamname, "
        		+ "u.branchid, b.branchname, "
        		+ "u.companyid, c.companyname, "
	    		+ "mobile, u.email, imgurl, imgthumburl "
	    		+ "FROM	tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblTeam t ON u.teamid = t.teamid "
				+ "LEFT JOIN tblBranch b ON u.branchid = b.branchid "
				+ "LEFT JOIN tblCompany c ON u.companyid = c.companyid "
				+ "WHERE u.email='" + email + "'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserLogin>() {
	 
	        @Override
	        public UserLogin extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserLogin userLogin = new UserLogin();
	                userLogin.setuserid(rs.getInt("userid"));
	                userLogin.setusername(rs.getString("username"));
	                userLogin.setpassword(rs.getString("password"));	                
	                userLogin.setrole(rs.getString("role"));
	                userLogin.setteamid(rs.getInt("teamid"));
	                userLogin.setteamname(rs.getString("teamname"));
	                userLogin.setbranchid(rs.getInt("branchid"));
	                userLogin.setbranchname(rs.getString("branchname"));
	                userLogin.setcompanyid(rs.getInt("companyid"));
	                userLogin.setcompanyname(rs.getString("companyname"));
	                userLogin.setmobile(rs.getString("mobile"));
	                userLogin.setemail(rs.getString("email"));
	                userLogin.setimgurl(rs.getString("imgurl"));
	                userLogin.setimgthumburl(rs.getString("imgthumburl"));
	                return userLogin;
	            }	 
	            return null;
	        }
        });
    }

    @Override
    public UserLogin get(String username) {
        String sql = "SELECT u.userid AS userid, "
	    		+ "u.username, password, role, "
        		+ "u.teamid, t.teamname, "
        		+ "u.branchid, b.branchname, "
        		+ "u.companyid, c.companyname, "
	    		+ "mobile, u.email, imgurl, imgthumburl "
	    		+ "FROM	tblUser u "        		
				+ "LEFT JOIN tblRole r ON u.username = r.username "
				+ "LEFT JOIN tblTeam t ON u.teamid = t.teamid "
				+ "LEFT JOIN tblBranch b ON u.branchid = b.branchid "
				+ "LEFT JOIN tblCompany c ON u.companyid = c.companyid "
				+ "WHERE u.username='" + username + "'";
        return this.getJdbcTemplate().query(sql, new ResultSetExtractor<UserLogin>() {
	 
	        @Override
	        public UserLogin extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                UserLogin userLogin = new UserLogin();
	                userLogin.setuserid(rs.getInt("userid"));
	                userLogin.setusername(rs.getString("username"));
	                userLogin.setpassword(rs.getString("password"));	                
	                userLogin.setrole(rs.getString("role"));
	                userLogin.setteamid(rs.getInt("teamid"));
	                userLogin.setteamname(rs.getString("teamname"));
	                userLogin.setbranchid(rs.getInt("branchid"));
	                userLogin.setbranchname(rs.getString("branchname"));
	                userLogin.setcompanyid(rs.getInt("companyid"));
	                userLogin.setcompanyname(rs.getString("companyname"));
	                userLogin.setmobile(rs.getString("mobile"));
	                userLogin.setemail(rs.getString("email"));
	                userLogin.setimgurl(rs.getString("imgurl"));
	                userLogin.setimgthumburl(rs.getString("imgthumburl"));
	                return userLogin;
	            }	 
	            return null;
	        }
        });
    }
    
    public boolean isExist(UserLogin userLogin) {
        return findUserEmail(userLogin.getemail())!=null;
    }    
}