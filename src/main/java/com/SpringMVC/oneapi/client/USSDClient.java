package com.SpringMVC.oneapi.client;

import com.SpringMVC.oneapi.model.common.InboundSMSMessage; 

public interface USSDClient { 
 
 /**
  * Send an USSD over OneAPI to one mobile terminal  
  * @return InboundSMSMessage 
  */ 
 InboundSMSMessage sendMessage(String address, String message); 
 
 /**
  * Stop USSD session 
  */ 
 void stopSession(String address, String message); 
 
}