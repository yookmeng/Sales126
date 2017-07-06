package com.SpringMVC.model;

import java.sql.Date;

public class Review {
    private int reviewid;
    private String period; 
    private int userid;
    private String username;
    private int teamid;
    private String teamname;
    private int branchid;
    private String branchname;
    private int companyid;
    private String companyname;
    private int targetid;
    private int teamtargetid;
    private Date reviewdate;
    private int prospect;
    private int testdrive;
    private int closed;
    private String minute;
    private int reviewby;
    private String reviewbyname;
    public Review() {
    }
 
    public Review(int reviewid, String period, int userid, String username, int teamid, String teamname, 
    		int branchid, String branchname, int companyid, String companyname,
    		int targetid, int teamtargetid, Date reviewdate, 
    		int prospect, int testdrive, int closed, String minute, 
    		int reviewby, String reviewbyname) {
        this.reviewid = reviewid;
        this.period = period;
        this.userid = userid;
        this.username = username;
        this.teamid = teamid;
        this.teamname = teamname;
        this.branchid = branchid;
        this.branchname = branchname;
        this.companyid = companyid;
        this.companyname = companyname;
        this.targetid = targetid;
        this.teamtargetid = teamtargetid;
        this.reviewdate = reviewdate;
        this.prospect = prospect;
        this.testdrive = testdrive;
        this.closed = closed;
        this.minute = minute;
        this.reviewby = reviewby;
        this.reviewbyname = reviewbyname;
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

    public int getteamid() {
        return teamid;
    }  
    public void setteamid(int teamid) {
        this.teamid = teamid;
    }

    public String getteamname() {
        return teamname;
    }  
    public void setteamname(String teamname) {
        this.teamname = teamname;
    }

    public int getbranchid() {
        return branchid;
    }  
    public void setbranchid(int branchid) {
        this.branchid = branchid;
    }

    public String getbranchname() {
        return branchname;
    }  
    public void setbranchname(String branchname) {
        this.branchname = branchname;
    }

    public int getcompanyid() {
        return companyid;
    }  
    public void setcompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getcompanyname() {
        return companyname;
    }  
    public void setcompanyname(String companyname) {
        this.companyname = companyname;
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

    public int getreviewby() {
        return reviewby;
    }  
    public void setreviewby(int reviewby) {
        this.reviewby = reviewby;
    }

    public String getreviewbyname() {
        return reviewbyname;
    }  
    public void setreviewbyname(String reviewbyname) {
        this.reviewbyname = reviewbyname;
    }
}