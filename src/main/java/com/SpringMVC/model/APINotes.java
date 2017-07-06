package com.SpringMVC.model;

import java.sql.Date;
import java.sql.Time;

public class APINotes {
    private String useremail;
    private int noteid;
    private Date notedate;
    private Time notetime;
    private int prospectid;
    private String prospectname;
    private String note;
    private int status;
    private String remark;
    public APINotes() {
    }
 
    public APINotes(String useremail, int noteid, Date notedate, Time notetime,
    		int prospectid, String prospectname, String note, 
    		int status, String remark) {
        this.useremail = useremail;
        this.noteid = noteid;
        this.notedate = notedate;
        this.notetime = notetime;
        this.prospectid = prospectid;
        this.prospectname = prospectname;
        this.note = note;
        this.status = status;
        this.remark = remark;
    }
 
    public String getuseremail() {
        return useremail;
    }  
    public void setuseremail(String useremail) {
        this.useremail = useremail;
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
}