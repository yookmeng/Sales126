package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.City;

@Repository 
public interface CityDAO {	
    
   public void save(City city);
    
   public void update(City city);

   public void delete(int cityid);
    
   public City get(int cityid);

   public List<City> list();
}