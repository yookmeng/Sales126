package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Company;
@Repository 
public interface CompanyDAO {
     
    public void save(Company company);
     
    public void update(Company company);

    public void delete(Company company);
     
    public Company get(int companyid);

    public List<Company> list();

    public List<String> companyList();
}