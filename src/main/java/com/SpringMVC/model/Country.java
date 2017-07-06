package com.SpringMVC.model;

public class Country {
    private int countryid;
    private String countrycode;
    private String countryname;
    private String iddcode;

    public Country() {
    }
 
    public Country(int countryid, String countrycode, String countryname, String iddcode) {
        this.countryid = countryid;
        this.countrycode = countrycode;
        this.countryname = countryname;
        this.iddcode = iddcode;
    }
 
    public int getcountryid() {
        return countryid;
    }  
    public void setcountryid(int countryid) {
        this.countryid = countryid;
    }

    public String getcountrycode() {
        return countrycode;
    }  
    public void setcountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getcountryname() {
        return countryname;
    }  
    public void setcountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getiddcode() {
        return iddcode;
    }  
    public void setiddcode(String iddcode) {
        this.iddcode = iddcode;
    }
}