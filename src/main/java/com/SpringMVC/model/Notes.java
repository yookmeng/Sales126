package com.SpringMVC.model;

import java.sql.Date;
import java.sql.Time;

public class Notes {
    private int noteid;
    private Date notedate;
    private Time notetime;
    private int userid;
    private String username;
    private int teamid;
    private String teamname;
    private int branchid;
    private String branchname;
    private int companyid;
    private String companyname;
    private int prospectid;
    private String prospectname;
    private String note;
    private int status;
    private String remark;
    private boolean read;
    private int reviewby;
    private String reviewbyname;
    private Date reviewdate;
    private Time reviewtime;
    public Notes() {
    }
 
    public Notes(int noteid, Date notedate, Time notetime, int userid, String username, 
    		int teamid, String teamname, int branchid, String branchname, 
    		int companyid, String companyname, int prospectid, String prospectname, 
    		String note, int status, String remark, boolean read,
    		int reviewby, String reviewbyname,
    		Date reviewdate, Time reviewtime) {
        this.noteid = noteid;
        this.notedate = notedate;
        this.notetime = notetime;
        this.userid = userid;
        this.username = username;
        this.teamid = teamid;
        this.teamname = teamname;
        this.branchid = branchid;
        this.branchname = branchname;
        this.companyid = companyid;
        this.companyname = companyname;
        this.prospectid = prospectid;
        this.prospectname = prospectname;
        this.note = note;
        this.status = status;
        this.remark = remark;
        this.read = read;
        this.reviewby = reviewby;
        this.reviewbyname = reviewbyname;
        this.reviewdate = reviewdate;
        this.reviewtime = reviewtime;
    }
 
    public int getnoteid() {
        return noteid;
    }  
    public void setnoteid(int noteid) {
        this.noteid = noteid;
    }

    public Date getnotedate() {
        return notedate;
    }  
    public void setnotedate(Date notedate) {
        this.notedate = notedate;
    }

    public Time getnotetime() {
        return notetime;
    }  
    public void setnotetime(Time notetime) {
        this.notetime = notetime;
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

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public String getprospectname() {
        return prospectname;
    }  
    public void setprospectname(String prospectname) {
        this.prospectname = prospectname;
    }

    public String getnote() {
        return note;
    }  
    public void setnote(String note) {
        this.note = note;
    }

    public int getstatus() {
        return status;
    }  
    public void setstatus(int status) {
        this.status = status;
    }

    public String getremark() {
        return remark;
    }  
    public void setremark(String remark) {
        this.remark = remark;
    }

    public boolean getread() {
        return read;
    }  
    public void setread(boolean read) {
        this.read= read;
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

    public Date getreviewdate() {
        return reviewdate;
    }  
    public void setreviewdate(Date reviewdate) {
        this.reviewdate = reviewdate;
    }

    public Time getreviewtime() {
        return reviewtime;
    }  
    public void setreviewtime(Time reviewtime) {
        this.reviewtime = reviewtime;
    }
}