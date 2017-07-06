package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Survey;
@Repository 
public interface SurveyDAO {
     
    public void saveOrUpdate(Survey survey);
     
    public void delete(int surveyid);
     
    public Survey get(int surveyid);
     
    public List<Survey> list(int prospectid);    
}