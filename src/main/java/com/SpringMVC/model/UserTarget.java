package com.SpringMVC.model;

public class UserTarget {
	private int targetid;
    private int userid;
    private String username;
    private String period;
	private int teamtargetid;
    private int prospect;
    private int testdrive;
    private int closed;

    public UserTarget() {
    }
 
    public UserTarget(int targetid, int userid, String username, String period, 
    		int teamtargetid, int prospect, int testdrive, int closed) {
        this.targetid = targetid;
        this.userid = userid;
        this.username = username;
        this.period = period;
        this.teamtargetid = teamtargetid;
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

    public String getperiod() {
        return period;
    }  
    public void setperiod(String period) {
        this.period = period;
    }

    public int getteamtargetid() {
        return teamtargetid;
    }  
    public void setteamtargetid(int teamtargetid) {
        this.teamtargetid = teamtargetid;
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