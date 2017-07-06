package com.SpringMVC.model;

import java.sql.Date;

public class Closed {
    private int closedid;
    private Date closedate;
    private int prospectid;
    private int activityid;
    private float downpayment;
    private String remark;
    
    public Closed() {
    }
 
    public Closed(int closedid, Date closedate, int prospectid, int activityid, 
    		float downpayment, String remark) {
        this.closedid = closedid;
        this.closedate = closedate;
        this.prospectid = prospectid;
        this.activityid = activityid;
        this.downpayment = downpayment;
        this.remark = remark;
    }
 
    public int getclosedid() {
        return closedid;
    }  
    public void setclosedid(int closedid) {
        this.closedid = closedid;
    }

    public Date getclosedate() {
        return closedate;
    }  
    public void setclosedate(Date closedate) {
        this.closedate = closedate;
    }

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public int getactivityid() {
        return activityid;
    }  
    public void setactivityid(int activityid) {
        this.activityid = activityid;
    }

    public float getdownpayment() {
        return downpayment;
    }  
    public void setdownpayment(float downpayment) {
        this.downpayment = downpayment;
    }

    public String getremark() {
        return remark;
    }  
    public void setremark(String remark) {
        this.remark = remark;
    }
}