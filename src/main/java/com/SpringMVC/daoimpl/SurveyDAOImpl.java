package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Survey;
import com.SpringMVC.dao.SurveyDAO;
import com.SpringMVC.mapper.SurveyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class SurveyDAOImpl extends JdbcDaoSupport implements SurveyDAO {
 
    @Autowired
    public SurveyDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void saveOrUpdate(Survey survey) {
        if (survey.getsurveyid() > 0)  {
            // update
            String sql = "UPDATE tblSurvey SET brand=?, model=?, mfgyear=?, tradein=?, remark=? "
            		+ "WHERE surveyid=?";
            this.getJdbcTemplate().update(sql, 
            		survey.getbrand(), survey.getmodel(), survey.getmfgyear(), 
            		survey.gettradein(), survey.getremark(), 
            		survey.getsurveyid());
        } else {
            // insert
            String sql = "INSERT INTO tblSurvey "
            		+ "(prospectid, surveydate, "
            		+ "brand, model, mfgyear, "
            		+ "tradein, remark) "
            		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
            this.getJdbcTemplate().update(sql, 
            		survey.getprospectid(), survey.getsurveydate(), 
            		survey.getbrand(), survey.getmodel(), survey.getmfgyear(), 
            		survey.gettradein(), survey.getremark());
            }
    }
    
    public void delete(int surveyid) {
        String sql = "DELETE FROM tblSurvey WHERE surveyid=?";
        this.getJdbcTemplate().update(sql, surveyid);
    }
    
    public List<Survey> list(int prospectid) {
        String sql = "SELECT * FROM tblSurvey WHERE prospectid = " + prospectid;
        SurveyMapper mapper = new SurveyMapper();
        List<Survey> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Survey get(int surveyid) {
	    String sql = "SELECT * FROM tblSurvey WHERE surveyid=" + surveyid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Survey>() {
	 
	        @Override
	        public Survey extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Survey survey = new Survey();
	                survey.setsurveyid(rs.getInt("surveyid"));
	                survey.setprospectid(rs.getInt("prospectid"));
	                survey.setsurveydate(rs.getDate("surveydate"));
	                survey.setbrand(rs.getString("brand"));
	                survey.setmodel(rs.getString("model"));
	                survey.setmfgyear(rs.getInt("mfgyear"));
	                survey.settradein(rs.getBoolean("tradein"));
	                survey.setremark(rs.getString("remark"));
	                return survey;
	            }	 
	            return null;
	        }
        });
    }
}