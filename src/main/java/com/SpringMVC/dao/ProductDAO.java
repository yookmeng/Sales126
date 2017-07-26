package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Product;
@Repository 
public interface ProductDAO {
     
    public void save(Product product);

    public void update(Product product);
     
    public void delete(int productid);

    public List<Product> getAll(int companyid);

    public Product get(int productid);

    public boolean isExist(Product product);
}