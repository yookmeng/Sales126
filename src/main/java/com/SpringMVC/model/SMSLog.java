package com.SpringMVC.model;

import java.sql.Date;
import java.sql.Time;

public class SMSLog {
    private int smsid;
    private int userid;
    private String username;
    private String usermobile;
    private int prospectid;
    private String firstname;
    private String lastname;
    private String mobile;
    private String message;
    private Date datesend;
    private Time timesend;
    public SMSLog() {
    }
 
    public SMSLog(int smsid, int userid, String username, String usermobile,
    		int prospectid, String firstname, String lastname, String mobile, 
    		String message, Date datesend, Time timesend) {
        this.smsid = smsid;
        this.userid = userid;
        this.username = username;
        this.usermobile = usermobile;
        this.prospectid = prospectid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.message = message;
        this.datesend = datesend;
        this.timesend = timesend;
    }
 
    public int getsmsid() {
        return smsid;
    }
    public void setsmsid(int smsid) {
        this.smsid = smsid;
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

    public String getusermobile() {
        return usermobile;
    }  
    public void setusermobile(String usermobile) {
        this.usermobile = usermobile;
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

    public String getmobile() {
        return mobile;
    }
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public String getmessage() {
        return message;
    }
    public void setmessage(String message) {
        this.message = message;
    }    

    public Date getdatesend() {
        return datesend;
    }  
    public void setdatesend(Date datesend) {
        this.datesend = datesend;
    }

    public Time gettimesend() {
        return timesend;
    }  
    public void settimesend(Time timesend) {
        this.timesend = timesend;
    }
}