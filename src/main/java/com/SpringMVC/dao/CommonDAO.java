package com.SpringMVC.dao;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Repository;
import com.SpringMVC.model.ExcelDetail;
import com.SpringMVC.model.UserMonthlySummary;

@Repository
public interface CommonDAO {	
    public List<String> periodList();
    
    public void sendEmail(HttpServletRequest request, List<ExcelDetail> prospect, List<UserMonthlySummary> summary, int userid, String period) throws IOException;

}