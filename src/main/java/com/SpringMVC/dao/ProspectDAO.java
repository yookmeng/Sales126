package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.ExcelDetail;
import com.SpringMVC.model.Prospect;
@Repository 
public interface ProspectDAO {
     
    public void save(Prospect prospect);
     
    public void update(Prospect prospect);

    public void delete(int prospectid);
     
    public Prospect get(int prospectid);
     
    public List<Prospect> list(int userid);
    
    public List<Prospect> listByTeam(int teamid);

    public List<Prospect> listByBranch(int branchid);

    public List<Prospect> listByCompany(int companyid);

    public List<ExcelDetail> listPeriod(int userid, String period, String userRole);

    public List<String> prospectList();

    public int getlastprospectid(int userid);
}