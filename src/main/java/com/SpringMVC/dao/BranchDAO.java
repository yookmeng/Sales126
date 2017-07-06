package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Branch;
@Repository 
public interface BranchDAO {
     
    public void save(Branch branch);
     
    public void update(Branch branch);

    public void delete(int branchid);
     
    public Branch get(int branchid);
     
    public Branch getByName(String branchname);

    public Branch getByMA(int maid);

    public List<Branch> list(int companyid);
    
    public List<String> branchList(int companyid);
}