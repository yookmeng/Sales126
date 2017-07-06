package com.SpringMVC.oneapi.listener;

import java.util.EventListener; 

import com.SpringMVC.oneapi.model.common.InboundSMSMessageList; 
 
public interface InboundMessageNotificationsListener extends EventListener { 
 public void onMessageReceived(InboundSMSMessageList inboundSMSMessageList); 
}