package com.SpringMVC.model;

import java.sql.Date;

public class Prospect {
    private int prospectid;
    private String firstname;
    private String lastname;
    private int userid;
    private String period;
    private String source;
    private boolean smsflag;
    private Date datecreated;
    private int brandid;
    private String brandname;
    private int modelid;
    private String modelname;
    private Address homeaddress;
    private String mobile;
    private String htelno;
    private int contactid;
    private Address workaddress;
    private String wtelno;
    private String gender;    
    private String email;
    private String status;
    private String statusname;

    public Prospect() {
    }
 
    public Prospect(int prospectid, String firstname, String lastname, 
    		int userid, String period, String source,  boolean smsflag, Date datecreated, 
    		int brandid, String brandname, int modelid, String modelname,
    		Address homeaddress, String mobile, String htelno, int contactid,
    		Address workaddress, String wtelno, 
    		String gender, String email, String status, String statusname) {
        this.prospectid = prospectid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.userid = userid;
        this.period = period;
        this.source = source;
        this.smsflag = smsflag;
        this.datecreated = datecreated;
        this.brandid = brandid;
        this.brandname = brandname;
        this.modelid = modelid;
        this.modelname = modelname;
        this.homeaddress = homeaddress;
        this.mobile = mobile;
        this.htelno = htelno;
        this.contactid = contactid;
        this.workaddress = workaddress;
        this.wtelno = wtelno;
        this.gender = gender;
        this.email = email;
        this.status = status;
        this.statusname = statusname;
    }
 
    public int getprospectid() {
        return prospectid;
    }
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
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

    public int getuserid() {
        return userid;
    }
    public void setuserid(int userid) {
        this.userid = userid;
    }

    public String getperiod() {
        return period;
    }  
    public void setperiod(String period) {
        this.period = period;
    }

    public String getsource() {
        return source;
    }
    public void setsource(String source) {
        this.source = source;
    }

    public boolean getsmsflag() {
        return smsflag;
    }
    public void setsmsflag(boolean smsflag) {
        this.smsflag = smsflag;
    }

    public Date getdatecreated() {
        return datecreated;
    }
    public void setdatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public int getbrandid() {
        return brandid;
    }
    public void setbrandid(int brandid) {
        this.brandid = brandid;
    }

    public String getbrandname() {
        return brandname;
    }
    public void setbrandname(String brandname) {
        this.brandname = brandname;
    }

    public int getmodelid() {
        return modelid;
    }
    public void setmodelid(int modelid) {
        this.modelid = modelid;
    }

    public String getmodelname() {
        return modelname;
    }
    public void setmodelname(String modelname) {
        this.modelname = modelname;
    }

    public Address gethomeaddress() {
        return homeaddress;
    }  
    public void sethomeaddress(Address homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getmobile() {
        return mobile;
    }
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public String gethtelno() {
        return htelno;
    }
    public void sethtelno(String htelno) {
        this.htelno = htelno;
    }

    public int getcontactid() {
        return contactid;
    }
    public void setcontactid(int contactid) {
        this.contactid = contactid;
    }

    public Address getworkaddress() {
        return workaddress;
    }  
    public void setworkaddress(Address workaddress) {
        this.workaddress = workaddress;
    }

    public String getwtelno() {
        return wtelno;
    }
    public void setwtelno(String wtelno) {
        this.wtelno = wtelno;
    }

    public String getgender() {
        return gender;
    }
    public void setgender(String gender) {
        this.gender = gender;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }    

    public String getstatus() {
        return status;
    }  
    public void setstatus(String status) {
        this.status = status;
    }

    public String getstatusname() {
        return statusname;
    }  
    public void setstatusname(String statusname) {
        this.statusname = statusname;
    }
}