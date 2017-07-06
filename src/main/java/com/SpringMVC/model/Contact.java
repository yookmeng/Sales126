package com.SpringMVC.model;

import java.sql.Date;

public class Contact {
    private int contactid;
    private int userid;
    private String firstname;
    private String lastname;
    private String mobile;
    private String home;
    private String work;
    private String email;
    private Date birthday;
    private Address address;
    private String company;
    private String title;    
    private String note;
    private String website;

    public Contact() {
    }
 
    public Contact(int contactid, int userid, String firstname, String lastname, 
    		String mobile, String home, String work, String email, Date birthday, 
    		Address address, String company, String title, String note, String website) {
        this.contactid = contactid;
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.home = home;
        this.work = work;
        this.email = email;
        this.birthday = birthday;
        this.address = address;        
        this.company = company;
        this.title = title;
        this.note = note;
        this.website= website;
    }
 
    public int getcontactid() {
        return contactid;
    }
    public void setcontactid(int contactid) {
        this.contactid = contactid;
    }

    public int getuserid() {
        return userid;
    }
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getfirstname() {
        return firstname;
    }  
    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getlastname() {
        return lastname;
    }  
    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public String getmobile() {
        return mobile;
    }
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public String gethome() {
        return home;
    }  
    public void sethome(String home) {
        this.home= home;
    }

    public String getwork() {
        return work;
    }
    public void setwork(String work) {
        this.work = work;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }

    public Date getbirthday() {
        return birthday;
    }
    public void setbirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Address getaddress() {
        return address;
    }
    public void setaddress(Address address) {
        this.address = address;
    }

    public String getcompany() {
        return company;
    }
    public void setcompany(String company) {
        this.company = company;
    }

    public String gettitle() {
        return title;
    }
    public void settitle(String title) {
        this.title = title;
    }

    public String getnote() {
        return note;
    }
    public void setnote(String note) {
        this.note = note;
    }

    public String getwebsite() {
        return website;
    }
    public void setwebsite(String website) {
        this.website = website;
    }    
}