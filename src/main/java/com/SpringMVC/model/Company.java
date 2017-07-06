package com.SpringMVC.model;

public class Company {
    private int companyid;
    private String companyname;
    private String regno;
    private int mdid;
    private String mdname;
    private Address address;
    private String telephone;
    private String fax;
    private String email;
    private String website;
    private int said;
    private String saname;

    public Company() {
    }
 
    public Company(int companyid, 
    		String companyname, String regno, int mdid, String mdname, 
    		Address address, String telephone, String fax, String email, String website, 
    		int said, String saname) {
        this.companyid = companyid;
        this.companyname = companyname;
        this.regno = regno;
        this.mdid = mdid;
        this.mdname = mdname;
        this.address = address;
        this.telephone = telephone;
        this.fax = fax;
        this.email = email;
        this.website = website;
        this.said = said;
        this.saname = saname;
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

    public String getregno() {
        return regno;
    }  
    public void setregno(String regno) {
        this.regno = regno;
    }
    
    public int getmdid() {
        return mdid;
    }  
    public void setmdid(int mdid) {
        this.mdid = mdid;
    }
    
    public String getmdname() {
        return mdname;
    }  
    public void setmdname(String mdname) {
        this.mdname = mdname;
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

    public int getsaid() {
        return said;
    } 
    public void setsaid(int said) {
        this.said = said;
    }

    public String getsaname() {
        return saname;
    } 
    public void setsaname(String saname) {
        this.saname = saname;
    }
}
