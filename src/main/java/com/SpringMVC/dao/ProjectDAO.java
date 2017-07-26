package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Project;
@Repository 
public interface ProjectDAO {
     
    public void save(Project project);

    public void update(Project project);
     
    public void delete(int projectid);

    public List<Project> getAll(int companyid);

    public Project get(int projectid);

    public boolean isExist(Project project);
}