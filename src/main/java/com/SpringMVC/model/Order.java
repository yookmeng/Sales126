package com.SpringMVC.model;

public class Order {
    private int orderid;
    private int projectid;
    private int productid;
    private String productname;
    private int prodtype;
    private String prodtypename;
    private int quantity;
    private float price;
    private float amount;
    
    public Order() {
    }
 
    public Order(int orderid, int projectid, int productid, String productname, 
    		int prodtype, String prodtypename, int quantity, float price, float amount) {
        this.orderid = orderid;
        this.projectid = projectid;
        this.productid = productid;
        this.productname = productname;
        this.prodtype = prodtype;
        this.prodtypename = prodtypename;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }
 
    public int getorderid() {
        return orderid;
    }  
    public void setorderid(int orderid) {
        this.orderid = orderid;
    }

    public int getprojectid() {
        return projectid;
    }  
    public void setprojectid(int projectid) {
        this.projectid = projectid;
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
}