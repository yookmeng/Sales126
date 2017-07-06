package com.SpringMVC.model;

public class Product {
    private int productid;
    private String productname;
    private int prodtype;
    private String prodtypename;
    private int companyid;
    private float price;
    private String productdesc1;
    private String productdesc2;
    private String productdesc3;
    
    public Product() {
    }
 
    public Product(int productid, String productname, int prodtype, String prodtypename, 
    		int companyid, float price, 
    		String productdesc1, String productdesc2, String productdesc3) {
        this.productid = productid;
        this.productname = productname;
        this.prodtype = prodtype;
        this.prodtypename = prodtypename;
        this.companyid = companyid;
        this.price = price;
        this.productdesc1 = productdesc1;
        this.productdesc2 = productdesc2;
        this.productdesc3 = productdesc3;
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

    public int getprodtype() {
        return prodtype;
    }  
    public void setprodtype(int prodtype) {
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

    public String getproductdesc1() {
        return productdesc1;
    }  
    public void setproductdesc1(String productdesc1) {
        this.productdesc1 = productdesc1;
    }

    public String getproductdesc2() {
        return productdesc2;
    }  
    public void setproductdesc2(String productdesc2) {
        this.productdesc2 = productdesc2;
    }

    public String getproductdesc3() {
        return productdesc3;
    }  
    public void setproductdesc3(String productdesc3) {
        this.productdesc3 = productdesc3;
    }
}