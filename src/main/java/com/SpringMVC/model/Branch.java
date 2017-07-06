package com.SpringMVC.model;

public class Branch {
    private int branchid;
    private String branchname;
    private int companyid;
    private String regno;
    private int maid;    
    private String maname;
    private Address address;
    private String telephone;
    private String fax;
    private String email;
    private String website;

    public Branch() {
    }
 
    public Branch(int branchid, String branchname, 
    		int companyid, String regno, int maid, String maname, 
    		Address address, String telephone, 
    		String fax,String email, String website) {
        this.branchid = branchid;
        this.branchname = branchname;
        this.companyid = companyid;
        this.regno = regno;
        this.maid = maid;
        this.maname = maname;
        this.address = address;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.website = website;
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

    public String getregno() {
        return regno;
    }
    public void setregno(String regno) {
        this.regno = regno;
    }

    public int getmaid() {
        return maid;
    }
    public void setmaid(int maid) {
        this.maid = maid;
    }

    public String getmaname() {
        return maname;
    }
    public void setmaname(String maname) {
        this.maname = maname;
    }

    public Address getaddress() {
        return address;
    }
    public void setaddress(Address address) {
        this.address = address;
    }

    public String gettelephone() {
        return telephone;
    }
    public void settelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getfax() {
        return fax;
    }
    public void setfax(String fax) {
        this.fax = fax;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }

    public String getwebsite() {
        return website;
    }
    public void setwebsite(String website) {
        this.website = website;
    }
}