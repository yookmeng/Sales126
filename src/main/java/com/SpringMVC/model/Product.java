package com.SpringMVC.model;

public class Product {
    private int productid;
    private String productname;
    private String prodtype;
    private String prodtypename;
    private int companyid;
    private float price;
    private String desc01;
    private String desc02;
    private String desc03;
    private String desc04;
    private String desc05;
    private String desc06;
    private String desc07;
    private String desc08;
    private String desc09;
    private String desc10;
    
    public Product() {
    }
 
    public Product(int productid, String productname, String prodtype, String prodtypename, 
    		int companyid, float price, 
    		String desc01, String desc02, String desc03, String desc04, String desc05,
    		String desc06, String desc07, String desc08, String desc09, String desc10) {
        this.productid = productid;
        this.productname = productname;
        this.prodtype = prodtype;
        this.prodtypename = prodtypename;
        this.companyid = companyid;
        this.price = price;
        this.desc01 = desc01;
        this.desc02 = desc02;
        this.desc03 = desc03;
        this.desc04 = desc04;
        this.desc05 = desc05;
        this.desc06 = desc06;
        this.desc07 = desc07;
        this.desc08 = desc08;
        this.desc09 = desc09;
        this.desc10 = desc10;
}
 
    public int getproductid() {
        return productid;
    }  
    public void setproductid(int productid) {
        this.productid = productid;
    }

    public String getproductname() {
        return productname;
    }  
    public void setproductname(String productname) {
        this.productname = productname;
    }

    public String getprodtype() {
        return prodtype;
    }  
    public void setprodtype(String prodtype) {
        this.prodtype = prodtype;
    }

    public String getprodtypename() {
        return prodtypename;
    }  
    public void setprodtypename(String prodtypename) {
        this.prodtypename = prodtypename;
    }

    public int getcompanyid() {
        return companyid;
    }  
    public void setcompanyid(int companyid) {
        this.companyid = companyid;
    }

    public float getprice() {
        return price;
    }  
    public void setprice(float price) {
        this.price = price;
    }

    public String getdesc01() {
        return desc01;
    }  
    public void setdesc01(String desc01) {
        this.desc01 = desc01;
    }

    public String getdesc02() {
        return desc02;
    }  
    public void setdesc02(String desc02) {
        this.desc02 = desc02;
    }

    public String getdesc03() {
        return desc03;
    }  
    public void setdesc03(String desc03) {
        this.desc03 = desc03;
    }

    public String getdesc04() {
        return desc04;
    }  
    public void setdesc04(String desc04) {
        this.desc04 = desc04;
    }

    public String getdesc05() {
        return desc05;
    }  
    public void setdesc05(String desc05) {
        this.desc05 = desc05;
    }

    public String getdesc06() {
        return desc06;
    }  
    public void setdesc06(String desc06) {
        this.desc06 = desc06;
    }

    public String getdesc07() {
        return desc07;
    }  
    public void setdesc07(String desc07) {
        this.desc07 = desc07;
    }

    public String getdesc08() {
        return desc08;
    }  
    public void setdesc08(String desc08) {
        this.desc08 = desc08;
    }

    public String getdesc09() {
        return desc09;
    }  
    public void setdesc09(String desc09) {
        this.desc09 = desc09;
    }

    public String getdesc10() {
        return desc10;
    }  
    public void setdesc10(String desc10) {
        this.desc10 = desc10;
    }
}