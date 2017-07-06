package com.SpringMVC.model;

public class Address {
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String street;

    public Address() {
    }
 
    public Address(String country, String zipcode, String state, String city, String street) {
        this.country = country;        
        this.zipcode = zipcode;
        this.state = state;
        this.city = city;
        this.street = street;        
    }
 
    public String getcountry() {
        return country;
    }
    public void setcountry(String country) {
        this.country = country;
    }

    public String getzipcode() {
        return zipcode;
    }
    public void setzipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getstate() {
        return state;
    }
    public void setstate(String state) {
        this.state = state;
    }

    public String getcity() {
        return city;
    }
    public void setcity(String city) {
        this.city = city;
    }

    public String getstreet() {
        return street;
    }  
    public void setstreet(String street) {
        this.street = street;
    }
}