package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Survey;
import org.springframework.jdbc.core.RowMapper;
 
public class SurveyMapper implements RowMapper<Survey> {
 
    @Override
    public Survey mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int surveyid = rs.getInt("surveyid");
        int prospectid = rs.getInt("prospectid");        
        Date surveydate = rs.getDate("surveydate");
        String brand = rs.getString("brand");
        String model = rs.getString("model");
        int mfgyear = rs.getInt("mfgyear");
        boolean tradein = rs.getBoolean("tradein");
        String remark = rs.getString("remark");
        
        return new Survey(surveyid, prospectid, surveydate, 
        		brand, model, mfgyear, tradein, remark);
    }
}