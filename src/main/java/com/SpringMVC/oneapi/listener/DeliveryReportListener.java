package com.SpringMVC.oneapi.listener;

import java.util.EventListener; 
import com.SpringMVC.oneapi.model.DeliveryReportList; 
 
public interface DeliveryReportListener extends EventListener { 
 public void onDeliveryReportReceived(DeliveryReportList deliveryReportList, Throwable error); 
}