package com.SpringMVC.model;

import java.sql.Date;

public class Project {
    private int projectid;
    private String projectname;
    private Address address;
    private int userid;
    private String name;
    private String mobile;
    private String email;
    private int titleid;
    private String titlename;
    private int propertyid;
    private String propertyname;
    private int units;
    private int quotationid;
    private float hardwarediscount;
    private float softwarediscount;
    private boolean smsflag;
    private Date datecreated;
    private String forecastperiod;
    private String status;
    private String statusname;

    public Project() {
    }
 
    public Project(int projectid, String projectname, Address address, int userid, 
    		String name, String mobile,  String email, 
    		int titleid, String titlename, int propertyid, String propertyname,
    		int units, int quotationid, float hardwarediscount, float softwarediscount,
    		boolean smsflag, Date datecreated, String forecastperiod,
    		String status, String statusname) {
        this.projectid = projectid;
        this.projectname = projectname;
        this.address = address;
        this.userid = userid;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.titleid = titleid;
        this.titlename = titlename;
        this.propertyid = propertyid;
        this.propertyname = propertyname;
        this.units = units;
        this.quotationid = quotationid;
        this.hardwarediscount = hardwarediscount;
        this.softwarediscount = softwarediscount;
        this.smsflag = smsflag;
        this.datecreated = datecreated;
        this.forecastperiod = forecastperiod;
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

    public int gettitleid() {
        return titleid;
    }
    public void settitleid(int titleid) {
        this.titleid = titleid;
    }
    
    public String gettitlename() {
        return titlename;
    }
    public void settitlename(String titlename) {
        this.titlename = titlename;
    }

    public int getpropertyid() {
        return propertyid;
    }
    public void setpropertyid(int propertyid) {
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

    public int getquotationid() {
        return quotationid;
    }
    public void setquotationid(int quotationid) {
        this.quotationid = quotationid;
    }
    
    public float gethardwarediscount() {
    	return hardwarediscount;
    }
    public void sethardwarediscount(float hardwarediscount){
    	this.hardwarediscount = hardwarediscount;
    }
    
    public float getsoftwarediscount() {
    	return softwarediscount;
    }
    public void setsoftwarediscount(float softwarediscount){
    	this.softwarediscount = softwarediscount;
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