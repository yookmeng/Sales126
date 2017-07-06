package com.SpringMVC.model;

import java.sql.Date;

public class Quotation {
    private int quotationid;
    private Date quotationdate;
    private int prospectid;
    private String firstname;
    private String lastname;
    private int activityid;
    private int brandid;
    private String brandname;
    private int modelid;
    private String modelname;
    private String colour;
    private float retailprice;
    private float suminsured;
    private String ncd;
    private float premium;
    private float premiumafterncd;
    private float roadtax;
    private float registrationfee;
    private float handlingcharges;
    private float extendedwarranty;
    private float othercharges;
    private float discount;
    private float quoteamount;
    private String term;
    private String remark;
    private String quotationpdflink;
    
    public Quotation() {
    }
 
    public Quotation(int quotationid, Date quotationdate, int prospectid, String firstname, String lastname,
    		int activityid, int brandid, String brandname, int modelid, String modelname, String colour,
    		float retailprice, float suminsured, String ncd, float premium, float premiumafterncd,
    		float roadtax, float registrationfee, float handlingcharges, float extendedwarranty,
    		float othercharges, float discount, float quoteamount, 
    		String term, String remark, String quotationpdflink) {

    	this.quotationid = quotationid;
    	this.quotationdate = quotationdate;
        this.prospectid = prospectid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.activityid = activityid;
        this.brandid = brandid;
        this.brandname = brandname;
        this.modelid = modelid;
        this.modelname = modelname;
        this.colour = colour;
        this.retailprice = retailprice;
        this.suminsured = suminsured;
        this.ncd = ncd;
        this.premium = premium;
        this.premiumafterncd = premiumafterncd;
        this.roadtax = roadtax;
        this.registrationfee = registrationfee;
        this.handlingcharges = handlingcharges;
        this.extendedwarranty = extendedwarranty;
        this.othercharges = othercharges;
        this.discount = discount;
        this.quoteamount = quoteamount;
        this.term = term;
        this.remark = remark;
        this.quotationpdflink = quotationpdflink;
    }
 
    public int getquotationid() {
        return quotationid;
    }  
    public void setquotationid(int quotationid) {
        this.quotationid = quotationid;
    }

    public Date getquotationdate() {
        return quotationdate;
    }  
    public void setquotationdate(Date quotationdate) {
        this.quotationdate = quotationdate;
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

    public int getactivityid() {
        return activityid;
    }  
    public void setactivityid(int activityid) {
        this.activityid = activityid;
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

    public String getcolour() {
        return colour;
    }  
    public void setcolour(String colour) {
        this.colour = colour;
    }

    public float getretailprice() {
        return retailprice;
    }  
    public void setretailprice(float retailprice) {
        this.retailprice = retailprice;
    }

    public float getsuminsured() {
        return suminsured;
    }  
    public void setsuminsured(float suminsured) {
        this.suminsured = suminsured;
    }

    public String getncd() {
        return ncd;
    }  
    public void setncd(String ncd) {
        this.ncd = ncd;
    }

    public float getpremium() {
        return premium;
    }  
    public void setpremium(float premium) {
        this.premium = premium;
    }

    public float getpremiumafterncd() {
        return premiumafterncd;
    }  
    public void setpremiumafterncd(float premiumafterncd) {
        this.premiumafterncd = premiumafterncd;
    }
    
    public float getroadtax() {
        return roadtax;
    }  
    public void setroadtax(float roadtax) {
        this.roadtax = roadtax;
    }

    public float getregistrationfee() {
        return registrationfee;
    }  
    public void setregistrationfee(float registrationfee) {
        this.registrationfee = registrationfee;
    }

    public float gethandlingcharges() {
        return handlingcharges;
    }  
    public void sethandlingcharges(float handlingcharges) {
        this.handlingcharges = handlingcharges;
    }

    public float getextendedwarranty() {
        return extendedwarranty;
    }  
    public void setextendedwarranty(float extendedwarranty) {
        this.extendedwarranty = extendedwarranty;
    }

    public float getothercharges() {
        return othercharges;
    }  
    public void setothercharges(float othercharges) {
        this.othercharges = othercharges;
    }

    public float getdiscount() {
        return discount;
    }  
    public void setdiscount(float discount) {
        this.discount = discount;
    }
    
    public float getquoteamount() {
        return quoteamount;
    }  
    public void setquoteamount(float quoteamount) {
        this.quoteamount = quoteamount;
    }

    public String getterm() {
        return term;
    }  
    public void setterm(String term) {
        this.term = term;
    }

    public String getremark() {
        return remark;
    }  
    public void setremark(String remark) {
        this.remark = remark;
    }

    public String getquotationpdflink() {
        return quotationpdflink;
    }  
    public void setquotationpdflink(String quotationpdflink) {
        this.quotationpdflink = quotationpdflink;
    }    
}