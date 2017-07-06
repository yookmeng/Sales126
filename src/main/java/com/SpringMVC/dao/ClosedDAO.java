package com.SpringMVC.dao;

import org.springframework.stereotype.Repository;
import com.SpringMVC.model.Closed;
@Repository 
public interface ClosedDAO {
    public void save(Closed closed);

    public void update(Closed closed);
     
    public void delete(int closedid);
     
    public Closed get(int closedid);         
}