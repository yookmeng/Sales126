package com.SpringMVC.oneapi.listener;

import java.util.EventListener; 

import com.SpringMVC.oneapi.model.common.InboundSMSMessageList; 
 
public interface InboundMessageListener extends EventListener { 
 public void onMessageRetrieved(InboundSMSMessageList inboundSMSMessageList, Throwable error); 
}