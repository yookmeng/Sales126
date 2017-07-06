package com.SpringMVC.model;

public class Team {
    private int teamid;
    private String teamname;
    private int branchid;    
    private int leaderid;
    private String leadername;

    public Team() {
    }
 
    public Team(int teamid, String teamname, int branchid, int leaderid, String leadername) {
        this.teamid = teamid;
        this.teamname = teamname;
        this.branchid = branchid;
        this.leaderid = leaderid;
        this.leadername = leadername;
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

    public int getleaderid() {
        return leaderid;
    }  
    public void setleaderid(int leaderid) {
        this.leaderid = leaderid;
    }

    public String getleadername() {
        return leadername;
    }  
    public void setleadername(String leadername) {
        this.leadername = leadername;
    }
}