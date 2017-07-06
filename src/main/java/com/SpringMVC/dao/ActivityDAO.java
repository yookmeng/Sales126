package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.SpringMVC.model.Activity;

@Repository 
public interface ActivityDAO {
     
    public void save(Activity activity);
     
    public void update(Activity activity);

    public void delete(int activityid);
     
    public Activity get(int activityid);
     
    public int getlastactivityid(int prospectid);

    public List<Activity> list(int prospectid);
    
    public List<Activity> listByUser(int userid);

    public List<Activity> listByTeam(int teamid);

    public List<Activity> listByBranch(int branchid);

    public List<Activity> listByCompany(int companyid);
}