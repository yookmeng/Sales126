package com.SpringMVC.oneapi.listener;

import java.util.EventListener; 

import com.SpringMVC.oneapi.model.common.LoginResponse; 
 
public interface LoginListener extends EventListener { 
    public void onLogin(LoginResponse response); 
}