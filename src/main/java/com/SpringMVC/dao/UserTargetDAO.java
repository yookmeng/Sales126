package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.UserTarget;
@Repository 
public interface UserTargetDAO {
     
    public void save(UserTarget userTarget);
     
    public void update(UserTarget userTarget);

    public void delete(int targetid);
     
    public UserTarget get(int targetid);

    public UserTarget getByPeriod(String period, int userid);

    public List<UserTarget> list(String period, int teamid);

    public List<UserTarget> listByCompany(int companyid);
    public List<UserTarget> listByBranch(int branchid);
    public List<UserTarget> listByTeam(int teamid);
    public List<UserTarget> listByUser(int userid);
}