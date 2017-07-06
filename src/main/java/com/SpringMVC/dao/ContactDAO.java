package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Contact;
@Repository 
public interface ContactDAO {
    public void save(Contact contact);

    public void update(Contact contact);
     
    public void delete(int contactid);
     
    public Contact get(int contactid);
     
    public Contact findByMobile(String mobile);
    
    public boolean isExist(Contact contact);
    
    public List<Contact> list(int userid);    
}