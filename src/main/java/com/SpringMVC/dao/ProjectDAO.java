package com.SpringMVC.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Order;
import com.SpringMVC.model.Project;

@Repository 
public interface ProjectDAO {
     
    public void save(Project project);

    public void update(Project project);
     
    public void delete(int projectid);
    
    public void createpdf(Project project, List<Order> listOrder, HttpServletRequest request);

    public Project get(int projectid);

    public List<Project> list(int userid);
    
    public List<Project> listByTeam(int teamid);

    public List<Project> listByBranch(int branchid);

    public List<Project> listByCompany(int companyid);

    public boolean isExist(Project project);
}