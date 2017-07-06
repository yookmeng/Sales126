package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Review;
@Repository 
public interface ReviewDAO {
     
    public void save(Review review);
     
    public void update(Review review);

    public void delete(int reviewid);
     
    public Review get(int reviewid);
     
    public List<Review> list(int userid);    

    public List<Review> listByTeam(int teamid);    

    public List<Review> listByBranch(int branchid);    

    public List<Review> listByCompany(int companyid);    

}