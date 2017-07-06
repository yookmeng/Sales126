package com.SpringMVC.model;

public class CompanyTarget {
    private int targetid;
    private int companyid;
    private String companyname;
    private String period;
    private int prospect;
    private int testdrive;
    private int closed;

    public CompanyTarget() {
    }
 
    public CompanyTarget(int targetid, int companyid, String companyname, 
    			String period, int prospect, int testdrive, int closed) {
        this.targetid = targetid;
        this.companyid = companyid;
        this.companyname = companyname;
        this.period  = period;        
        this.prospect = prospect;
        this.testdrive = testdrive;
        this.closed = closed;
    }
 
    public int gettargetid() {
        return targetid;
    }  
    public void settargetid(int targetid) {
        this.targetid = targetid;
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

    public String getperiod() {
        return period;
    }  
    public void setperiod(String period) {
        this.period = period;
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
}