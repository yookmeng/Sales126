package com.SpringMVC.model;

public class TeamTarget {
    private int targetid;
    private int teamid;
    private String teamname;
    private int branchtargetid;
    private String period;
    private int prospect;
    private int testdrive;
    private int closed;

    public TeamTarget() {
    }
 
    public TeamTarget(int targetid, int teamid, String teamname, String period, 
    		int branchtargetid, int prospect, int testdrive, int closed) {
        this.targetid = targetid;
        this.teamid = teamid;
        this.teamname = teamname;
        this.branchtargetid = branchtargetid;
        this.period = period;
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

    public String getperiod() {
        return period;
    }  
    public void setperiod(String period) {
        this.period = period;
    }

    public int getbranchtargetid() {
        return branchtargetid;
    }  
    public void setbranchtargetid(int branchtargetid) {
        this.branchtargetid = branchtargetid;
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