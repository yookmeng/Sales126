package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.SMSLog;
@Repository 
public interface SMSLogDAO {
     
    public void save(SMSLog smsLog);
     
    public void send(String recipient, String message);

    public SMSLog get(int smsid);     
    
    public List<SMSLog> list(int userid);    
    public List<SMSLog> listByTeam(int teamid);    
    public List<SMSLog> listByBranch(int branchid);    
    public List<SMSLog> listByCompany(int companyid);    
}