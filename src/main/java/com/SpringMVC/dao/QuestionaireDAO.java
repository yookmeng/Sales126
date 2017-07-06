package com.SpringMVC.dao;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.Questionaire;

@Repository 
public interface QuestionaireDAO {
	
    public void save(Questionaire questionaire);

}