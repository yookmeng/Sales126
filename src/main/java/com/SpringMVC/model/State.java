package com.SpringMVC.model;

public class State {
    private int stateid;
    private int countryid;
    private String countryname;
    private String statename;

    public State() {
    }
 
    public State(int stateid, int countryid, String countryname, String statename) {
        this.stateid = stateid;
        this.countryid = countryid;
        this.countryname = countryname;
        this.statename = statename;
    }
 
    public int getstateid() {
        return stateid;
    }  
    public void setstateid(int stateid) {
        this.stateid = stateid;
    }

    public int getcountryid() {
        return countryid;
    }      
    public void setcountryid(int countryid) {
        this.countryid = countryid;
    }
    
    public String getcountryname() {
        return countryname;
    }      
    public void setcountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getstatename() {
        return statename;
    }  
    public void setstatename(String statename) {
        this.statename = statename;
    }
}