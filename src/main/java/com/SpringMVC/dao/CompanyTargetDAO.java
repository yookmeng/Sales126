package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.CompanyTarget;
@Repository 
public interface CompanyTargetDAO {
     
    public void save(CompanyTarget companyTarget);
     
    public void update(CompanyTarget companyTarget);

    public void delete(int targetid);
     
    public CompanyTarget get(int targetid);

    public CompanyTarget getByPeriod(String period, int companyid);

    public List<CompanyTarget> list(int companyid);
}