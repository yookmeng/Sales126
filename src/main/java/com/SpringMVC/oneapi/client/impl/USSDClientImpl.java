package com.SpringMVC.oneapi.client.impl;

import com.SpringMVC.oneapi.client.USSDClient; 
import com.SpringMVC.oneapi.config.Configuration; 
import com.SpringMVC.oneapi.model.RequestData; 
import com.SpringMVC.oneapi.model.USSDRequest; 
import com.SpringMVC.oneapi.model.RequestData.Method; 
import com.SpringMVC.oneapi.model.common.InboundSMSMessage; 
 
public class USSDClientImpl extends OneAPIBaseClientImpl implements USSDClient { 
 
 private static final String USSD_URL_BASE = "/ussd/outbound"; 
  
 public USSDClientImpl(Configuration configuration) { 
  super(configuration); 
 } 
 
 /**
  * Send an USSD over OneAPI to one mobile terminal ' 
  * @return InboundSMSMessage 
  */ 
 @Override 
 public InboundSMSMessage sendMessage(String address, String message) { 
  RequestData requestData = new RequestData(USSD_URL_BASE, Method.POST); 
  requestData.setFormParams(new USSDRequest(address, message)); 
        requestData.setContentType(JSON_CONTENT_TYPE); 
  return executeMethod(requestData, InboundSMSMessage.class); 
 } 
 
 /**
  * Stop USSD session 
  */ 
 @Override 
 public void stopSession(String address, String message) { 
  RequestData requestData = new RequestData(USSD_URL_BASE, Method.POST); 
  requestData.setFormParams(new USSDRequest(address, message, true)); 
  requestData.setContentType(JSON_CONTENT_TYPE); 
     executeMethod(requestData); 
 } 
}