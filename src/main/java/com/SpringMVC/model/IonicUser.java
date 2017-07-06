package com.SpringMVC.model;

public class IonicUser {
    private String username;
    private String email;
    private String password;
    private String name;
    private String image;
     
    public IonicUser()  {         
    }
 
    public IonicUser(String username, String email, String password, 
    		String name, String image) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.image= image;
    }
 
    public String getusername() {
        return username;
    }
    public void setusername(String username) {
        this.username = username;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }

    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }
 
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }

    public String getimage() {
        return image;
    }
    public void setimage(String image) {
        this.image = image;
    }
}