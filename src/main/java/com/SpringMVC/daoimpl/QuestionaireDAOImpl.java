package com.SpringMVC.daoimpl;
 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;
 
import com.SpringMVC.model.Questionaire;
import com.SpringMVC.dao.QuestionaireDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class QuestionaireDAOImpl extends JdbcDaoSupport implements QuestionaireDAO {
 
    @Autowired
    public QuestionaireDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(Questionaire questionaire) {
		Connection conn = this.getConnection();
    	try {
			conn.setAutoCommit(true);
	    	CallableStatement proc = conn.prepareCall("{ ? = call spQuestionaire("
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
	    			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
	    	proc.registerOutParameter(1, Types.OTHER);
	    	proc.setInt(2, questionaire.getuserid());
	    	proc.setString(3, questionaire.getprospectname());
	    	proc.setString(4, questionaire.getmobile());
	    	proc.setString(5, questionaire.getemail());
	    	proc.setInt(6, questionaire.getbrandid());
	    	proc.setInt(7, questionaire.getmodelid());
	    	proc.setString(8, questionaire.getsource());
	    	proc.setString(9, questionaire.getstatus());
	    	proc.setBoolean(10, questionaire.getsmsflag());
	    	proc.setBoolean(11, questionaire.getdemo());
	    	proc.setDate(12, questionaire.getdemodate());
	    	proc.setTime(13, questionaire.getdemotime());
	    	proc.setBoolean(14, questionaire.gettestdrive());
	    	proc.setDate(15, questionaire.gettestdrivedate());
	    	proc.setTime(16, questionaire.gettestdrivetime());
	    	proc.setBoolean(17, questionaire.getquotation());
	    	proc.setDate(18, questionaire.getquotationdate());
	    	proc.setTime(19, questionaire.getquotationtime());
	    	proc.setDate(20, questionaire.getstatusdate());
	    	proc.setTime(21, questionaire.getstatustime());
	    	proc.execute();
	    	proc.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    	
    }    
}