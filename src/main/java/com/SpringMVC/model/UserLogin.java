package com.SpringMVC.model;

public class UserLogin {
	private int userid;
    private String username;
    private String password;
    private String role;
    private int teamid;
    private String teamname;
    private int branchid;
    private String branchname;
    private int companyid;
    private String companyname;
    private String mobile;
    private String email;
    private String imgurl;
    private String imgthumburl;
     
    public UserLogin()  {         
    }
 
    public UserLogin(int userid, String username, String password, String role, 
    		int teamid, String teamname, int branchid, String branchname, 
    		int companyid, String companyname, String mobile, String email,
    		String imgurl, String imgthumburl) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.role = role;
        this.teamid = teamid;
        this.teamname = teamname;
        this.branchid = branchid;
        this.branchname = branchname;
        this.companyid = companyid;
        this.companyname = companyname;
        this.mobile = mobile;
        this.email = email;
        this.imgurl = imgurl;
        this.imgthumburl = imgthumburl;
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

    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }
 
    public String getrole() {
        return role;
    }
    public void setrole(String role) {
        this.role = role;
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

    public String getimgurl() {
        return imgurl;
    }
    public void setimgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getimgthumburl() {
        return imgthumburl;
    }
    public void setimgthumburl(String imgthumburl) {
        this.imgthumburl = imgthumburl;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserLogin other = (UserLogin) obj;
        if (userid != other.userid)
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "UserLogin [userid=" + userid + ", username=" + username + ", password=" + password
                + ", role=" + role + ", companyid=" + companyid + "]";
    }    
}