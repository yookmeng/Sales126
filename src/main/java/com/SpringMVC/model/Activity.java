package com.SpringMVC.model;

import java.sql.Date;
import java.sql.Time;

public class Activity {
    private int activityid;
    private int userid;
    private String username;
    private int prospectid;
    private String firstname;
    private String lastname;
    private Date activitydate;
    private Time activitytime;
    private int brandid;
    private String brandname;
    private int modelid;
    private String modelname;
    private boolean demo;
    private Date demodate;
    private Time demotime;
    private boolean testdrive;
    private Date testdrivedate;
    private Time testdrivetime;
    private boolean quotation;
    private Date quotationdate;
    private Time quotationtime;
    private int quotationid;
    private String quotationpdflink;
    private boolean closed;
    private Date closeddate;
    private Time closedtime;
    
    public Activity() {
    }
 
    public Activity(int activityid, int userid, String username, 
    		int prospectid, String firstname, String lastname, 
    		Date activitydate, Time activitytime,
    		int brandid, String brandname, int modelid, String modelname, 
    		boolean demo, Date demodate, Time demotime,
    		boolean testdrive, Date testdrivedate, Time testdrivetime,
    		boolean quotation, Date quotationdate, Time quotationtime,
    		int quotationid, String quotationpdflink, 
    		boolean closed, Date closeddate, Time closedtime) {
        this.activityid = activityid;
        this.userid = userid;
        this.username = username;
        this.prospectid = prospectid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.activitydate = activitydate;
        this.activitytime = activitytime;
        this.brandid = brandid;
        this.brandname = brandname;
        this.modelid = modelid;
        this.modelname = modelname;
        this.demo = demo;
        this.demodate = demodate;
        this.demotime = demotime;
        this.testdrive = testdrive;
        this.testdrivedate = testdrivedate;
        this.testdrivetime = testdrivetime;
        this.quotation = quotation;
        this.quotationdate = quotationdate;
        this.quotationtime = quotationtime;
        this.quotationid = quotationid;
        this.quotationpdflink = quotationpdflink;
        this.closed = closed;
        this.closeddate = closeddate;
        this.closedtime = closedtime;
    }
 
    public int getactivityid() {
        return activityid;
    }  
    public void setactivityid(int activityid) {
        this.activityid = activityid;
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

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public String getfirstname() {
        return firstname;
    }  
    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }  
    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getactivitydate() {
        return activitydate;
    }  
    public void setactivitydate(Date activitydate) {
        this.activitydate = activitydate;
    }

    public Time getactivitytime() {
        return activitytime;
    }  
    public void setactivitytime(Time activitytime) {
        this.activitytime = activitytime;
    }

    public int getbrandid() {
        return brandid;
    }  
    public void setbrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getbrandname() {
        return brandname;
    }  
    public void setbrandname(String brandname) {
        this.brandname = brandname;
    }

    public int getmodelid() {
        return modelid;
    }  
    public void setmodelid(int modelid) {
        this.modelid = modelid;
    }

    public String getmodelname() {
        return modelname;
    }  
    public void setmodelname(String modelname) {
        this.modelname = modelname;
    }

    public boolean getdemo() {
        return demo;
    }  
    public void setdemo(boolean demo) {
        this.demo = demo;
    }

    public Date getdemodate() {
        return demodate;
    }
    public void setdemodate(Date demodate) {
        this.demodate = demodate;
    }

    public Time getdemotime() {
        return demotime;
    }
    public void setdemotime(Time demotime) {
        this.demotime = demotime;
    }

    public boolean gettestdrive() {
        return testdrive;
    }  
    public void settestdrive(boolean testdrive) {
        this.testdrive = testdrive;
    }

    public Date gettestdrivedate() {
        return testdrivedate;
    }
    public void settestdrivedate(Date testdrivedate) {
        this.testdrivedate = testdrivedate;
    }

    public Time gettestdrivetime() {
        return testdrivetime;
    }
    public void settestdrivetime(Time testdrivetime) {
        this.testdrivetime = testdrivetime;
    }
    
    public boolean getquotation() {
        return quotation;
    }  
    public void setquotation(boolean quotation) {
        this.quotation = quotation;
    }

    public Date getquotationdate() {
        return quotationdate;
    }
    public void setquotationdate(Date quotationdate) {
        this.quotationdate = quotationdate;
    }

    public Time getquotationtime() {
        return quotationtime;
    }
    public void setquotationtime(Time quotationtime) {
        this.quotationtime = quotationtime;
    }
    
    public int getquotationid() {
        return quotationid;
    }  
    public void setquotationid(int quotationid) {
        this.quotationid = quotationid;
    }

    public String getquotationpdflink() {
        return quotationpdflink;
    }  
    public void setquotationpdflink(String quotationpdflink) {
        this.quotationpdflink = quotationpdflink;
    }

    public boolean getclosed() {
        return closed;
    }  
    public void setclosed(boolean closed) {
        this.closed = closed;
    }

    public Date getcloseddate() {
        return closeddate;
    }
    public void setcloseddate(Date closeddate) {
        this.closeddate = closeddate;
    }

    public Time getclosedtime() {
        return closedtime;
    }
    public void setclosedtime(Time closedtime) {
        this.closedtime = closedtime;
    }
}