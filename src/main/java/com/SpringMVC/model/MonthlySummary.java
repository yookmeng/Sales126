package com.SpringMVC.model;

public class MonthlySummary {
    private String email;
    private String period;
     
    public MonthlySummary()  {         
    }
 
    public MonthlySummary(String email, String period) {
        this.email = email;
        this.period = period;
    }
 
    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }

    public String getperiod() {
        return period;
    }
    public void setperiod(String period) {
        this.period = period;
    }
}