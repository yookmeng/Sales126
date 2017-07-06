package com.SpringMVC.oneapi.listener;

import java.util.EventListener; 

import com.SpringMVC.oneapi.model.RoamingNotification; 
 
public interface HLRNotificationsListener extends EventListener { 
 public void OnHLRReceived(RoamingNotification roamingNotification); 
}