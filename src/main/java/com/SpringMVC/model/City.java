package com.SpringMVC.model;

public class City {
    private int cityid;
    private int countryid;
    private int stateid;
    private String countryname;
    private String statename;
    private String cityname;

    public City() {
    }
 
    public City(int cityid, int stateid, int countryid, 
    		String countryname, String statename, String cityname) {
        this.cityid = cityid;
        this.stateid = stateid;
        this.countryid = countryid;
        this.countryname = countryname;
        this.statename = statename;
        this.cityname = cityname;
    }
 
    public int getcityid() {
        return cityid;
    }  
    public void setcityid(int cityid) {
        this.cityid = cityid;
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

    public String getcityname() {
        return cityname;
    }  
    public void setcityname(String cityname) {
        this.cityname = cityname;
    }
}