package com.SpringMVC.model;

import java.sql.Date;

public class APIReview {
    private String useremail;
    private int reviewid;
    private String period;
    private String email;    
    private int targetid;
    private int teamtargetid;
    private Date reviewdate;
    private int prospect;
    private int testdrive;
    private int closed;
    private String minute;
    public APIReview() {
    }
 
    public APIReview(String useremail, int reviewid, String period, String email, 
    		int targetid, int teamtargetid, Date reviewdate, 
    		int prospect, int testdrive, int closed, String minute) {
        this.useremail = useremail;
        this.reviewid = reviewid;
        this.period = period;
        this.email = email;
        this.targetid = targetid;
        this.teamtargetid = teamtargetid;
        this.reviewdate = reviewdate;
        this.prospect = prospect;
        this.testdrive = testdrive;
        this.closed = closed;
        this.minute = minute;
    }
 
    public String getuseremail() {
        return useremail;
    }  
    public void setuseremail(String useremail) {
        this.useremail = useremail;
    }

    public int getreviewid() {
        return reviewid;
    }  
    public void setreviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public String getperiod() {
        return period;
    }  
    public void setperiod(String period) {
        this.period = period;
    }

    public String getemail() {
        return email;
    }  
    public void setemail(String email) {
        this.email = email;
    }

    public int gettargetid() {
        return targetid;
    }  
    public void settargetid(int targetid) {
        this.targetid = targetid;
    }

    public int getteamtargetid() {
        return teamtargetid;
    }  
    public void setteamtargetid(int teamtargetid) {
        this.teamtargetid = teamtargetid;
    }

    public Date getreviewdate() {
        return reviewdate;
    }  
    public void setreviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
    }

    public int getprospect() {
        return prospect;
    }  
    public void setprospect(int prospect) {
        this.prospect = prospect;
    }

    public int gettestdrive() {
        return testdrive;
    }  
    public void settestdrive(int testdrive) {
        this.testdrive = testdrive;
    }

    public int getclosed() {
        return closed;
    }  
    public void setclosed(int closed) {
        this.closed = closed;
    }

    public String getminute() {
        return minute;
    }  
    public void setminute(String minute) {
        this.minute = minute;
    }
}