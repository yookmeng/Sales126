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
        // insert
    	Address address = project.getaddress();
        String sql = "INSERT INTO tblProject (projectname, "
        		+ "address.country, address.zipcode, address.state, address.city, address.street, "
        		+ "userid, name, mobile, email, titleid, propertyid, units, datecreated, status) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		project.getprojectname(), 
        		address.getcountry(), address.getzipcode(), address.getstate(), address.getcity(), address.getstreet(),
        		project.getuserid(), project.getname(), project.getmobile(), project.getemail(),
        		project.gettitleid(), project.getpropertyid(), project.getunits(), 
        		project.getdatecreated(), project.getstatus());
    }
    
    public void update(Project project) {
        // update
    	Address address = project.getaddress();
        String sql = "UPDATE tblProject SET projectname=?, "
	    		+ "address.country=?, address.zipcode=?, address.state=?, address.city=?, address.street=?, "
        		+ "name=?, mobile=?, email=?, titleid=?, propertyid=?, units=?, quotationid=?, "
        		+ "hardwarediscount=?, softwarediscount=?, smsflag=?, forecastperiod=?, status=? "
        		+ "WHERE projectid=?";
        this.getJdbcTemplate().update(sql, 
        		project.getprojectname(), 
        		address.getcountry(), address.getzipcode(), address.getstate(), address.getcity(), address.getstreet(),
        		project.getname(), project.getmobile(), project.getemail(), 
        		project.gettitleid(), project.getpropertyid(), project.getunits(), 
        		project.getquotationid(), project.gethardwarediscount(), project.getsoftwarediscount(),
        		project.getsmsflag(), project.getforecastperiod(), project.getstatus(),
        		project.getprojectid());
    }

    public void delete(int projectid) {
        String sql = "DELETE FROM tblProject WHERE projectid=?";
        this.getJdbcTemplate().update(sql, projectid);
    }
    
    public List<Project> getAll(int companyid) {
        String sql = "SELECT proj.projectid, proj.projectname, "
	    		+ "(proj.address).country AS country, "
	    		+ "(proj.address).zipcode AS zipcode, "
	    		+ "(proj.address).state AS state, "
	    		+ "(proj.address).city AS city, "
	    		+ "(proj.address).street AS street, "
        		+ "proj.userid, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.quotationid, proj.hardwarediscount, proj.softwarediscount, "
        		+ "proj.smsflag, proj.datecreated, "
        		+ "proj.status, status.codename AS statusname "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
        		+ "WHERE proj.companyid = " + companyid;
        ProjectMapper mapper = new ProjectMapper();
        List<Project> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Project get(int projectid) {
        String sql = "SELECT proj.projectid, proj.projectname, "
	    		+ "(proj.address).country AS country, "
	    		+ "(proj.address).zipcode AS zipcode, "
	    		+ "(proj.address).state AS state, "
	    		+ "(proj.address).city AS city, "
	    		+ "(proj.address).street AS street, "
        		+ "proj.userid, proj.name, proj.mobile, proj.email, "
        		+ "proj.titleid, title.codename AS titlename, "
        		+ "proj.propertyid, property.codename AS propertyname, "
        		+ "proj.units, proj.quotationid, proj.hardwarediscount, proj.softwarediscount, "
        		+ "proj.smsflag, proj.datecreated, "
        		+ "proj.status, status.codename AS statusname "
        		+ "FROM tblProject proj "
        		+ "LEFT JOIN tblCodeMaster title ON title.codetype = 'TITLE' AND title.codeid = proj.titleid "        		
        		+ "LEFT JOIN tblCodeMaster property ON property.codetype = 'PROPERTY' AND property.codeid = proj.propertyid "        		
        		+ "LEFT JOIN tblCodeMaster status ON status.codetype = 'STATUS' AND status.codeid = proj.status "        		
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
	                address.setcountry(rs.getString("country"));
	                address.setzipcode(rs.getString("zipcode"));
	                address.setstate(rs.getString("state"));
	                address.setcity(rs.getString("city"));
	                address.setstreet(rs.getString("street"));
	                project.setaddress(address);
	            	project.setuserid(rs.getInt("userid"));
	            	project.setname(rs.getString("name"));
	            	project.setmobile(rs.getString("mobile"));
	            	project.setemail(rs.getString("email"));
	                project.settitleid(rs.getInt("titleid"));
	            	project.settitlename(rs.getString("titlename"));
	                project.setpropertyid(rs.getInt("propertyid"));
	            	project.setpropertyname(rs.getString("propertyname"));
	                project.setunits(rs.getInt("units"));
	                project.setquotationid(rs.getInt("quotationid"));
	                project.sethardwarediscount(rs.getFloat("hardwarediscount"));
	                project.setsoftwarediscount(rs.getFloat("softwarediscount"));
	                project.setsmsflag(rs.getBoolean("smsflag"));
	                project.setdatecreated(rs.getDate("datecreated"));
	            	project.setforecastperiod(rs.getString("forecastperiod"));
	            	project.setstatus(rs.getString("status"));
	            	project.setstatusname(rs.getString("statusname"));	                
	                return project;
	            }	 
	            return null;
	        }
        });
    }

   public boolean isExist(Project project) {
        return get(project.getprojectid())!=null;
    }
}