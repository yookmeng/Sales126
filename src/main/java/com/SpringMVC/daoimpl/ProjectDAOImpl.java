	package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;

import com.SpringMVC.model.Address;
import com.SpringMVC.model.Project;
import com.SpringMVC.dao.ProjectDAO;
import com.SpringMVC.mapper.ProjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ProjectDAOImpl extends JdbcDaoSupport implements ProjectDAO { 
    @Autowired
    public ProjectDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Project project) {
    	Address address = project.getaddress();
        // insert
        String sql = "INSERT INTO tblProject (projectname, address.country, address.zipcode, "
        		+ "address.state, address.city, address.street, userid, "
        		+ "name, mobile, email, titleid, "
        		+ "propertyid, units, smsflag, "
        		+ "datecreated, forecastperiod, hwdiscount, swdiscount, status) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		project.getprojectname(), address.getcountry(), address.getzipcode(), 
        		address.getstate(), address.getcity(), address.getstreet(), project.getuserid(), 
        		project.getname(), project.getmobile(), project.getemail(), project.gettitleid(), 
        		project.getpropertyid(), project.getunits(), project.getsmsflag(),
        		project.getdatecreated(), project.getforecastperiod(), 
        		project.gethwdiscount(), project.getswdiscount(), project.getstatus());
    }
    
    public void update(Project project) {
    	Address address = project.getaddress();
        // update
        String sql = "UPDATE tblProject SET projectname=?, address.country=?, "
        		+ "address.zipcode=?, address.state=?, address.city=?, address.street=?, "
        		+ "name=?, mobile=?, email=?, titleid=?, propertyid=?, "
        		+ "units=?, forecastperiod=?, hwdiscount=?, swdiscount=?, "
        		+ "status=? "
        		+ "WHERE projectid=?";
        this.getJdbcTemplate().update(sql, 
        		project.getprojectname(), address.getcountry(), address.getzipcode(), 
        		address.getstate(), address.getcity(), address.getstreet(), 
        		project.getname(), project.getmobile(), project.getemail(), 
        		project.gettitleid(), project.getpropertyid(), project.getunits(), 
        		project.getforecastperiod(), project.gethwdiscount(), project.getswdiscount(), 
        		project.getstatus(), project.getprojectid());
    }

    public void delete(int projectid) {
        String sql = "DELETE FROM tblProject WHERE projectid=?";
        this.getJdbcTemplate().update(sql, projectid);
    }
    
    public Project get(int projectid) {
        String sql = "SELECT proj.projectid, proj.projectname, proj.address, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE proj.projectid = " + projectid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Project>() {
	 
	        @Override
	        public Project extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	Project project = new Project();
	            	project.setprojectid(rs.getInt("projectid"));
	            	project.setprojectname(rs.getString("projectname"));
	                Address address = new Address();
	                address.setcountry(rs.getString("address.country"));
	                address.setzipcode(rs.getString("address.zipcode"));
	                address.setstate(rs.getString("address.state"));
	                address.setcity(rs.getString("address.city"));
	                address.setstreet(rs.getString("address.street"));
	                project.setaddress(address);
	            	project.setuserid(rs.getInt("userid"));
	            	project.setusername(rs.getString("username"));
	            	project.setname(rs.getString("name"));
	            	project.setmobile(rs.getString("mobile"));
	            	project.setemail(rs.getString("email"));
	                project.settitleid(rs.getString("titleid"));
	            	project.settitlename(rs.getString("titlename"));
	                project.setpropertyid(rs.getString("propertyid"));
	            	project.setpropertyname(rs.getString("propertyname"));
	                project.setunits(rs.getInt("units"));
	                project.setsmsflag(rs.getBoolean("smsflag"));
	                project.setdatecreated(rs.getDate("datecreated"));
	            	project.setforecastperiod(rs.getString("forecastperiod"));
	            	project.sethwdiscount(rs.getFloat("hwdiscount"));
	            	project.setswdiscount(rs.getFloat("swdiscount"));
	            	project.setstatus(rs.getString("status"));
	            	project.setstatusname(rs.getString("statusname"));	                
	                return project;
	            }	 
	            return null;
	        }
        });
    }

    public List<Project> list(int userid) {
        String sql = "SELECT proj.projectid, proj.projectname, proj.address.country, "
        		+ "proj.address.zipcode, proj.address.state, proj.address.city, proj.address.street, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.userid = " + userid + " "
        		+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
    public List<Project> listByTeam(int teamid) {
        String sql = "SELECT proj.projectid, proj.projectname, proj.address.country, "
        		+ "proj.address.zipcode, proj.address.state, proj.address.city, proj.address.street, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.teamid = " + teamid + " "
        		+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Project> listByBranch(int branchid) {
        String sql = "SELECT proj.projectid, proj.projectname, proj.address.country, "
        		+ "proj.address.zipcode, proj.address.state, proj.address.city, proj.address.street, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.branchid = " + branchid + " "
        		+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public List<Project> listByCompany(int companyid) {
        String sql = "SELECT proj.projectid, proj.projectname, proj.address.country, "
        		+ "proj.address.zipcode, proj.address.state, proj.address.city, proj.address.street, "
        		+ "proj.userid, usr.username, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.smsflag, proj.datecreated, proj.forecastperiod, "
        		+ "proj.hwdiscount, proj.swdiscount, "
        		+ "proj.status, status.codename AS statusname "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "LEFT JOIN tblUser usr ON usr.userid = proj.userid "
        		+ "WHERE usr.companyid = " + companyid + " "
        		+ "ORDER BY projectname";
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }
    
   public boolean isExist(Project project) {
        return get(project.getprojectid())!=null;
    }
}