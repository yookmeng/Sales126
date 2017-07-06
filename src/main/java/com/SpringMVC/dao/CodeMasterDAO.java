package com.SpringMVC.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.SpringMVC.model.CodeMaster;

@Repository 
public interface CodeMasterDAO {	
    
   public void save(CodeMaster codeMaster);
    
   public void update(CodeMaster codeMaster);

   public void delete(String codeid);
    
   public CodeMaster get(String codeid);

   public List<CodeMaster> list(String codetype);

   public List<String> getType(String codetype);
}