package com.SpringMVC.model;

public class ExcelDetail {
	private String period;
	private int branchid;
	private String branchname;
	private int teamid;
	private String teamname;
	private int userid;
	private String username;
	private int prospectid;
	private String firstname;
	private String lastname;
	private String mobile;
	private String email;
	private String brandname;
	private String modelname;
	private String demo;
	private String testdrive;
	private String quotation;
	private String status;
	private String statusname;
	private String closed;
	private String lost;
	private String diary;
	private String remark;

    public ExcelDetail() {
    }
 
    public ExcelDetail(String period, int branchid, String branchname, 
    		int teamid, String teamname, int userid, String username, 
    		int prospectid, String firstname, String lastname, String mobile, String email, 
    		String brandname, String modelname, String demo, String testdrive, String quotation, 
    		String status, String statusname, String closed, String lost, String diary, String remark) {
        this.period = period;
        this.branchid = branchid;
        this.branchname = branchname;
        this.teamid = teamid;
        this.teamname = teamname;
        this.userid = userid;
        this.username = username;
        this.prospectid = prospectid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
        this.email = email;
        this.brandname = brandname;
        this.modelname = modelname;
        this.demo = demo;
        this.testdrive = testdrive;
        this.quotation = quotation;
        this.status = status;
        this.statusname = statusname;
        this.closed = closed;
        this.lost = lost;
        this.diary = diary;
        this.remark = remark;
    }

    public String getperiod() {
        return period;
    }  
    public void setperiod(String period) {
        this.period = period;
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

    public String getmobile() {
        return mobile;
    }  
    public void setmobile(String mobile) {
        this.mobile = mobile;
    }

    public String getemail() {
        return email;
    }  
    public void setemail(String email) {
        this.email = email;
    }

    public String getbrandname() {
        return brandname;
    }  
    public void setbrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getmodelname() {
        return modelname;
    }  
    public void setmodelname(String modelname) {
        this.modelname = modelname;
    }

    public String getdemo() {
        return demo;
    }  
    public void setdemo(String demo) {
        this.demo = demo;
    }

    public String gettestdrive() {
        return testdrive;
    }  
    public void settestdrive(String testdrive) {
        this.testdrive = testdrive;
    }

    public String getquotation() {
        return quotation;
    }  
    public void setquotation(String quotation) {
        this.quotation = quotation;
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

    public String getclosed() {
        return closed;
    }  
    public void setclosed(String closed) {
        this.closed = closed;
    }
    
    public String getlost() {
        return lost;
    }  
    public void setlost(String lost) {
        this.lost = lost;
    }
    
    public String getdiary() {
        return diary;
    }  
    public void setdiary(String diary) {
        this.diary = diary;
    }
    
    public String getremark() {
        return remark;
    }  
    public void setremark(String remark) {
        this.remark = remark;
    }
}