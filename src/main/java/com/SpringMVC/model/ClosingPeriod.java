package com.SpringMVC.model;

import java.sql.Date;

public class ClosingPeriod {
    private int id;
    private int companyid;
    private String period;
    private Date opendate;
    private Date closedate;
    private Boolean closed;

    public ClosingPeriod() {
    }
 
    public ClosingPeriod(int id, int companyid, String period, 
    			Date opendate, Date closedate, Boolean closed) {
        this.id = id;
        this.companyid = companyid;
        this.period = period;
        this.opendate = opendate;        
        this.closedate = closedate;
        this.closed = closed;
    }
 
    public int getid() {
        return id;
    }  
    public void setid(int id) {
        this.id = id;
    }

    public int getcompanyid() {
        return companyid;
    }  
    public void setcompanyid(int companyid) {
        this.companyid = companyid;
    }

    public String getperiod() {
        return period;
    }  
    public void setperiod(String period) {
        this.period = period;
    }

    public Date getopendate() {
        return opendate;
    }  
    public void setopendate(Date opendate) {
        this.opendate = opendate;
    }

    public Date getclosedate() {
        return closedate;
    }  
    public void setclosedate(Date closedate) {
        this.closedate = closedate;
    }

    public Boolean getclosed() {
        return closed;
    }  
    public void setclosed(Boolean closed) {
        this.closed = closed;
    }
}