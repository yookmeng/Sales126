package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.BranchTarget;
@Repository 
public interface BranchTargetDAO {
     
    public void save(BranchTarget branchTarget);

    public void update(BranchTarget branchTarget);
     
    public void delete(int targetid);
     
    public BranchTarget get(int targetid);

    public BranchTarget getByPeriod(String period, int branchid);

    public List<BranchTarget> list(String period, int companyid);

    public List<BranchTarget> listByCompany(int companyid);

    public List<BranchTarget> listByBranch(int branchid);
}