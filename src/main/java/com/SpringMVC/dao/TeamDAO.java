package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Team;
@Repository 
public interface TeamDAO {
     
    public void save(Team team);
     
    public void update(Team team);

    public void delete(int teamid);
     
    public Team get(int teamid);
     
    public Team getByName(String teamname);

    public Team getByUser(int userid);

    public List<Team> list(int branchid);
    
    public List<Team> listByCompany(int companyid);

    public List<String> teamList(int branchid);
}