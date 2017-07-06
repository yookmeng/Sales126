package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.TeamTarget;
@Repository 
public interface TeamTargetDAO {
     
    public void save(TeamTarget teamTarget);
     
    public void update(TeamTarget teamTarget);

    public void delete(int targetid);
     
    public TeamTarget get(int targetid);

    public TeamTarget getByPeriod(String period, int teamid);

    public TeamTarget getByTeam(int teamid);

    public List<TeamTarget> list(String period, int branchid);

    public List<TeamTarget> listAll(String period, int companyid);

    public List<TeamTarget> listByCompany(int companyid);
    
    public List<TeamTarget> listByBranch(int branchid);
   
    public List<TeamTarget> listByTeam(int teamid);
}