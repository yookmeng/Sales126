package com.SpringMVC.model;

public class BranchTarget {
	private int targetid;
	private int branchid;
	private String branchname;
    private String period;        
	private int companytargetid;
    private int prospect;
    private int testdrive;
    private int closed;

    public BranchTarget() {
    }
 
    public BranchTarget(int targetid, int branchid, String branchname, String period, 
    		int companytargetid, int prospect, int testdrive, int closed) {
        this.targetid = targetid;
        this.branchid = branchid;
        this.branchname = branchname;
        this.period = period;
        this.companytargetid = companytargetid;        
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

    public String getperiod() {
        return period;
    }  
    public void setperiod(String period) {
        this.period = period;
    }

    public int getcompanytargetid() {
        return companytargetid;
    }  
    public void setcompanytargetid(int companytargetid) {
        this.companytargetid = companytargetid;
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