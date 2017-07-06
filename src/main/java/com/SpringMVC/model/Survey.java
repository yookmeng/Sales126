package com.SpringMVC.model;

import java.sql.Date;

public class Survey {
    private int surveyid;
    private int prospectid;
    private Date surveydate;
    private String brand;
    private String model;
    private int mfgyear;
    private boolean tradein;
    private String remark;
    
    public Survey() {
    }
 
    public Survey(int surveyid, int prospectid, Date surveydate, 
    		String brand, String model, int mfgyear, 
    		boolean tradein, String remark) {
        this.surveyid = surveyid;
        this.prospectid = prospectid;
        this.surveydate = surveydate;
        this.brand = brand;
        this.model = model;
        this.mfgyear = mfgyear;
        this.tradein = tradein;
        this.remark = remark;
    }
 
    public int getsurveyid() {
        return surveyid;
    }  
    public void setsurveyid(int surveyid) {
        this.surveyid = surveyid;
    }

    public int getprospectid() {
        return prospectid;
    }  
    public void setprospectid(int prospectid) {
        this.prospectid = prospectid;
    }

    public Date getsurveydate() {
        return surveydate;
    }  
    public void setsurveydate(Date surveydate) {
        this.surveydate = surveydate;
    }

    public String getbrand() {
        return brand;
    }  
    public void setbrand(String brand) {
        this.brand = brand;
    }

    public String getmodel() {
        return model;
    }  
    public void setmodel(String model) {
        this.model = model;
    }

    public int getmfgyear() {
        return mfgyear;
    }  
    public void setmfgyear(int mfgyear) {
        this.mfgyear = mfgyear;
    }

    public boolean gettradein() {
        return tradein;
    }  
    public void settradein(boolean tradein) {
        this.tradein = tradein;
    }

    public String getremark() {
        return remark;
    }  
    public void setremark(String remark) {
        this.remark = remark;
    }
}