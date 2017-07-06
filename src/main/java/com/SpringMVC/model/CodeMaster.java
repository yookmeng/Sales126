package com.SpringMVC.model;

public class CodeMaster {
    private String codetype;
    private String codeid;
    private String codename;

    public CodeMaster() {
    }
 
    public CodeMaster(String codetype, String codeid, String codename) {
        this.codetype = codetype;
        this.codeid = codeid;
        this.codename = codename;
    }
 
    public String getcodetype() {
        return codetype;
    }  
    public void setcodetype(String codetype) {
        this.codetype = codetype;
    }

    public String getcodeid() {
        return codeid;
    }  
    public void setcodeid(String codeid) {
        this.codeid = codeid;
    }

    public String getcodename() {
        return codename;
    }  
    public void setcodename(String codename) {
        this.codename = codename;
    }
}