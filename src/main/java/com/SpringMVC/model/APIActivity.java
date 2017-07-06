package com.SpringMVC.model;

import java.sql.Date;
import java.sql.Time;

public class APIActivity {
    private String useremail;
    private int activityid;
    private Date activitydate;
    private Time activitytime;
    private int prospectid;
    private String brandname;
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
    private boolean closed;
    private Date closeddate;
    private Time closedtime;
    
    public APIActivity() {
    }
 
    public APIActivity(String useremail, int activityid, 
    		Date activitydate, Time activitytime, 
    		int prospectid, String brandname, String modelname, 
    		boolean demo, Date demodate, Time demotime, 
    		boolean testdrive, Date testdrivedate, Time testdrivetime,
    		boolean quotation, Date quotationdate, Time quotationtime,
    		boolean closed, Date closeddate, Time closedtime) {
        this.useremail = useremail;
        this.activityid = activityid;
        this.activitydate = activitydate;
        this.activitytime = activitytime;
        this.prospectid = prospectid;
        this.brandname = brandname;
        this.modelname = modelname;
        this.demo = demo;
        this.demodate = demodate;
        this.demotime = demotime;
        this.testdrive = testdrive;
        this.testdrivedate= testdrivedate;
        this.testdrivetime= testdrivetime;
        this.quotation = quotation;
        this.quotationdate = quotationdate;
        this.quotationtime = quotationtime;
        this.closed = closed;
        this.closeddate = closeddate;
        this.closedtime = closedtime;
    }
 
    public String getuseremail() {
        return useremail;
    }  
    public void setuseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getactivityid() {
        return activityid;
    }  
    public void setactivityid(int activityid) {
        this.activityid = activityid;
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

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public String getbrandname() {
        return brandname;
    }  
    public void setbrandname(String brandname) {
        this.brandname = brandname;
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