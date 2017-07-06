package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Event;
@Repository 
public interface EventDAO {
     
    public void save(Event event);
     
    public void update(Event event);

    public void delete(int eventid);
     
    public Event get(int eventid);
     
    public List<Event> list(int userid);    
    public List<Event> listByTeam(int teamid);    
    public List<Event> listByBranch(int branchid);    
    public List<Event> listByCompany(int companyid);    
}