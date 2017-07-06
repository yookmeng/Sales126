package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.SpringMVC.model.UserMonthlySummary;

@Repository 
public interface UserMonthlySummaryDAO {
	
    public UserMonthlySummary get(String period, int userid, String role);

    public List<UserMonthlySummary> list(String period, int userid, String role); 

    public List<UserMonthlySummary> listAll(String period, int userid, String role); 
}