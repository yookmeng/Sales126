package com.SpringMVC.model;

public class Model {
    private int modelid;
    private String modelname;
    private int brandid;
    private boolean sellingmodel;
    private float commission;
    private float price;
    private float suminsured;
    private float premium;
    private float roadtax;    
    private String colour;
    private String enginetype;
    private String fuelsupplysystem;
    private String displacement;
    private String maxpower;
    private String maxtorque;
    private String transmission;
    
    public Model() {
    }
 
    public Model(int modelid, String modelname, int brandid, boolean sellingmodel, 
    		float commission, float price, float suminsured, float premium, float roadtax,
    		String colour, String enginetype, String fuelsupplysystem, String displacement,
    		String maxpower, String maxtorque, String transmission) {
        this.modelid = modelid;
        this.modelname = modelname;
        this.brandid = brandid;
        this.sellingmodel = sellingmodel;
        this.commission = commission;
        this.price = price;
        this.suminsured = suminsured;
        this.premium = premium;
        this.roadtax = roadtax;
        this.colour = colour;
        this.enginetype = enginetype;
        this.fuelsupplysystem = fuelsupplysystem;
        this.displacement = displacement;
        this.maxpower = maxpower;
        this.maxtorque = maxtorque;
        this.transmission = transmission;
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

    public int getbrandid() {
        return brandid;
    }  
    public void setbrandid(int brandid) {
        this.brandid = brandid;
    }

    public boolean getsellingmodel() {
        return sellingmodel;
    }  
    public void setsellingmodel(boolean sellingmodel) {
        this.sellingmodel = sellingmodel;
    }

    public float getcommission() {
        return commission;
    }  
    public void setcommission(float commission) {
        this.commission = commission;
    }

    public float getprice() {
        return price;
    }  
    public void setprice(float price) {
        this.price = price;
    }

    public float getsuminsured() {
        return suminsured;
    }  
    public void setsuminsured(float suminsured) {
        this.suminsured = suminsured;
    }

    public float getpremium() {
        return premium;
    }  
    public void setpremium(float premium) {
        this.premium = premium;
    }

    public float getroadtax() {
        return roadtax;
    }  
    public void setroadtax(float roadtax) {
        this.roadtax = roadtax;
    }

    public String getcolour() {
        return colour;
    }  
    public void setcolour(String colour) {
        this.colour = colour;
    }

    public String getenginetype() {
        return enginetype;
    }  
    public void setenginetype(String enginetype) {
        this.enginetype = enginetype;
    }

    public String getfuelsupplysystem() {
        return fuelsupplysystem;
    }  
    public void setfuelsupplysystem(String fuelsupplysystem) {
        this.fuelsupplysystem = fuelsupplysystem;
    }

    public String getdisplacement() {
        return displacement;
    }  
    public void setdisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getmaxpower() {
        return maxpower;
    }  
    public void setmaxpower(String maxpower) {
        this.maxpower = maxpower;
    }

    public String getmaxtorque() {
        return maxtorque;
    }  
    public void setmaxtorque(String maxtorque) {
        this.maxtorque = maxtorque;
    }

    public String gettransmission() {
        return transmission;
    }  
    public void settransmission(String transmission) {
        this.transmission = transmission;
    }
}