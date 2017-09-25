package com.SpringMVC.model;

import java.sql.Date;

public class Project {
    private int projectid;
    private String projectname;
    private Address address;
    private int userid;
    private String username;
    private String name;
    private String mobile;
    private String email;
    private String titleid;
    private String titlename;
    private String propertyid;
    private String propertyname;
    private int units;
    private boolean smsflag;
    private Date datecreated;
    private String forecastperiod;
    private float hwdiscount;
    private float swdiscount;
    private String status;
    private String statusname;

    public Project() {
    }
 
    public Project(int projectid, String projectname, Address address, int userid, 
    		String username, String name, String mobile,  String email, 
    		String titleid, String titlename, String propertyid, String propertyname,
    		int units, boolean smsflag, Date datecreated, String forecastperiod,
    		float hwdiscount, float swdiscount, String status, String statusname) {
        this.projectid = projectid;
        this.projectname = projectname;
        this.address = address;
        this.userid = userid;
        this.username = username;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.titleid = titleid;
        this.titlename = titlename;
        this.propertyid = propertyid;
        this.propertyname = propertyname;
        this.units = units;
        this.smsflag = smsflag;
        this.datecreated = datecreated;
        this.forecastperiod = forecastperiod;
        this.hwdiscount = hwdiscount;
        this.swdiscount = swdiscount;
        this.status = status;
        this.statusname = statusname;
    }
 
    public int getprojectid() {
        return projectid;
    }
    public void setprojectid(int projectid) {
        this.projectid = projectid;
    }

    public String getprojectname() {
        return projectname;
    }  
    public void setprojectname(String projectname) {
        this.projectname = projectname;
    }

    public Address getaddress() {
        return address;
    }  
    public void setaddress(Address address) {
        this.address = address;
    }

    public int getuserid() {
        return userid;
    }
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }

    public String getmobile() {
        return mobile;
    }
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }    

    public String gettitleid() {
        return titleid;
    }
    public void settitleid(String titleid) {
        this.titleid = titleid;
    }
    
    public String gettitlename() {
        return titlename;
    }
    public void settitlename(String titlename) {
        this.titlename = titlename;
    }

    public String getpropertyid() {
        return propertyid;
    }
    public void setpropertyid(String propertyid) {
        this.propertyid = propertyid;
    }
    
    public String getpropertyname() {
        return propertyname;
    }
    public void setpropertyname(String propertyname) {
        this.propertyname = propertyname;
    }

    public int getunits() {
        return units;
    }
    public void setunits(int units) {
        this.units = units;
    }

    public boolean getsmsflag() {
        return smsflag;
    }
    public void setsmsflag(boolean smsflag) {
        this.smsflag = smsflag;
    }

    public Date getdatecreated() {
        return datecreated;
    }
    public void setdatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public String getforecastperiod() {
        return forecastperiod;
    }  
    public void setforecastperiod(String forecastperiod) {
        this.forecastperiod = forecastperiod;
    }

    public float gethwdiscount() {
        return hwdiscount;
    }  
    public void sethwdiscount(float hwdiscount) {
        this.hwdiscount = hwdiscount;
    }

    public float getswdiscount() {
        return swdiscount;
    }  
    public void setswdiscount(float swdiscount) {
        this.swdiscount = swdiscount;
    }

    public String getstatus() {
        return status;
    }  
    public void setstatus(String status) {
        this.status = status;
    }

    public String getstatusname() {
        return statusname;
    }  
    public void setstatusname(String statusname) {
        this.statusname = statusname;
    }
}