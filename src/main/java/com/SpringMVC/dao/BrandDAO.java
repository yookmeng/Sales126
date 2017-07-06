package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Brand;
@Repository 
public interface BrandDAO {
     
    public void save(Brand brand);

    public void update(Brand brand);
     
    public void delete(int brandid);
     
    public List<String> getSellingBrands(int companyid);

    public List<String> getAllBrands(int companyid);

    public Brand get(int brandid);
     
    public Brand getByName(String brandname);

    public List<Brand> list(int companyid);    
}