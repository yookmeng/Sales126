package com.SpringMVC.model;

public class Calendar {
    private int id;
    private String title;
    private String start;
    private String end;
    private String url;    
    private boolean allDay;
    
    public Calendar() {
    }
 
    public Calendar(int id, String title, String start, String end, String url, boolean allDay) {
        this.id = id;
        this.title = title;
        this.start= start;
        this.end = end;
        this.url = url;
        this.allDay = allDay;
    }
 
    public int getid() {
        return id;
    }  
    public void setid(int id) {
        this.id = id;
    }

    public String gettitle() {
        return title;
    }  
    public void settitle(String title) {
        this.title = title;
    }

    public String getstart() {
        return start;
    }  
    public void setstart(String start) {
        this.start = start;
    }

    public String getend() {
        return end;
    }  
    public void setend(String end) {
        this.end = end;
    }

    public String geturl() {
        return url;
    }  
    public void seturl(String url) {
        this.url = url;
    }
    
    public boolean getallDay() {
        return allDay;
    }  
    public void setallDay(boolean allDay) {
        this.allDay = allDay;
    }
}