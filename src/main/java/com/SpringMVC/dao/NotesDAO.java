package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Notes;
@Repository 
public interface NotesDAO {
     
    public void save(Notes notes);
     
    public void update(Notes notes);

    public void delete(int noteid);
     
    public Notes get(int noteid);
     
    public List<Notes> listByProspect(int prospectid);    

    public List<Notes> list(int userid);    

    public List<Notes> listByTeam(int teamid);    

    public List<Notes> listByBranch(int branchid);    

    public List<Notes> listByCompany(int companyid);    

}