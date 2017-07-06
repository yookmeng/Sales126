package com.SpringMVC.model;

public class UserMonthlySummary {
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
    private int targetprospect;
    private int targettestdrive;
    private int targetclosed;
    private int actualprospect;
    private int actualtestdrive;
    private int actualclosed;
    private float percentprospect;
    private float percenttestdrive;
    private float percentclosed;
    private float commission;
    private int totalhot;
    private int pendingactivity;

    public UserMonthlySummary() {
    }
 
    public UserMonthlySummary(String period, int userid, String username, 
    		int teamid, String teamname, int branchid, String branchname, 
    		int companyid, String companyname, int targetid,
    		int targetprospect, int targettestdrive, int targetclosed,
    		int actualprospect, int actualtestdrive, int actualclosed,
    		float percentprospect, float percenttestdrive, float percentclosed,
    		float commission, int totalhot, int pendingactivity) {
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
        this.targetprospect = targetprospect;
        this.targettestdrive = targettestdrive;
        this.targetclosed = targetclosed;
        this.actualprospect = actualprospect;
        this.actualtestdrive = actualtestdrive;
        this.actualclosed = actualclosed;
        this.percentprospect = percentprospect;
        this.percenttestdrive = percenttestdrive;
        this.percentclosed = percentclosed;
        this.commission = commission;
        this.totalhot = totalhot;
        this.pendingactivity = pendingactivity;
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

    public int gettargetprospect() {
        return targetprospect;
    }  
    public void settargetprospect(int targetprospect) {
        this.targetprospect = targetprospect;
    }

    public int gettargettestdrive() {
        return targettestdrive;
    }  
    public void settargettestdrive(int targettestdrive) {
        this.targettestdrive= targettestdrive;
    }

    public int gettargetclosed() {
        return targetclosed;
    }  
    public void settargetclosed(int targetclosed) {
        this.targetclosed = targetclosed;
    }

    public int getactualprospect() {
        return actualprospect;
    }  
    public void setactualprospect(int actualprospect) {
        this.actualprospect = actualprospect;
    }

    public int getactualtestdrive() {
        return actualtestdrive;
    }  
    public void setactualtestdrive(int actualtestdrive) {
        this.actualtestdrive = actualtestdrive;
    }

    public int getactualclosed() {
        return actualclosed;
    }  
    public void setactualclosed(int actualclosed) {
        this.actualclosed = actualclosed;
    }

    public float getpercentprospect() {
        return percentprospect;
    }  
    public void setpercentprospect(float percentprospect) {
        this.percentprospect = percentprospect;
    }

    public float getpercenttestdrive() {
        return percenttestdrive;
    }  
    public void setpercenttestdrive(float percenttestdrive) {
        this.percenttestdrive = percenttestdrive;
    }

    public float getpercentclosed() {
        return percentclosed;
    }  
    public void setpercentclosed(float percentclosed) {
        this.percentclosed = percentclosed;
    }

    public float getcommission() {
        return commission;
    }  
    public void setcommission(float commission) {
        this.commission = commission;
    }

    public int gettotalhot() {
        return totalhot;
    }  
    public void settotalhot(int totalhot) {
        this.totalhot = totalhot;
    }

    public int getpendingactivity() {
        return pendingactivity;
    }  
    public void setpendingactivity(int pendingactivity) {
        this.pendingactivity = pendingactivity;
    }
}