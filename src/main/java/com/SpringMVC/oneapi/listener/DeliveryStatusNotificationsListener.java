package com.SpringMVC.oneapi.listener;

import java.util.EventListener; 

import com.SpringMVC.oneapi.model.DeliveryInfoNotification; 
 
public interface DeliveryStatusNotificationsListener extends EventListener { 
 public void onDeliveryStatusNotificationReceived(DeliveryInfoNotification deliveryInfoNotification); 
}
