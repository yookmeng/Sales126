package com.SpringMVC.model;

import java.sql.Date;

public class Order {
    private int orderid;
    private Date orderdate;
    private int projectid;
    private String projectname;
    private int productid;
    private String productname;
    private int prodtype;
    private String prodtypename;
    private int quantity;
    private float price;
    private float amount;
    private float hwdiscount;
    private float swdiscount;
    
    public Order() {
    }
 
    public Order(int orderid, Date orderdate, int projectid, String projectname, 
    		int productid, String productname, int prodtype, String prodtypename, 
    		int quantity, float price, float amount, float hwdiscount, float swdiscount) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.projectid = projectid;
        this.projectname = projectname;
        this.productid = productid;
        this.productname = productname;
        this.prodtype = prodtype;
        this.prodtypename = prodtypename;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
        this.hwdiscount = hwdiscount;
        this.swdiscount = swdiscount;
    }
 
    public int getorderid() {
        return orderid;
    }  
    public void setorderid(int orderid) {
        this.orderid = orderid;
    }

    public Date getorderdate() {
        return orderdate;
    }  
    public void setorderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int getprojectid() {
        return projectid;
    }  
    public void setprojectid(int projectid) {
        this.projectid = projectid;
    }

    public String getprojectname() {
        return projectname;
    }  
    public void setprojectname(String projectname) {
        this.projectname = projectname;
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

    public int getquantity() {
        return quantity;
    }  
    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public float getprice() {
        return price;
    }  
    public void setprice(float price) {
        this.price = price;
    }

    public float getamount() {
        return amount;
    }  
    public void setamount(float amount) {
        this.amount = amount;
    }

    public float gethwdiscount() {
        return hwdiscount;
    }  
    public void sethwdiscount(float hwdiscount) {
        this.hwdiscount = hwdiscount;
    }

    public float getswdiscount() {
        return swdiscount;
    }  
    public void setswdiscount(float swdiscount) {
        this.swdiscount = swdiscount;
    }
}