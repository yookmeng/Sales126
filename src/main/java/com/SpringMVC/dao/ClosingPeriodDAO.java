package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.ClosingPeriod;
@Repository 
public interface ClosingPeriodDAO {
    public void save(ClosingPeriod closingPeriod);

    public void update(ClosingPeriod closingPeriod);
     
    public void delete(int id);
     
    public ClosingPeriod get(int id);
         
    public String getCurrentPeriod(int companyid);

    public List<ClosingPeriod> list(int companyid);    

    public List<String> getPeriod(int companyid);
}