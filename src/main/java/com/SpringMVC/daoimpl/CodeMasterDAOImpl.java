package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource; 
import com.SpringMVC.dao.CodeMasterDAO;
import com.SpringMVC.mapper.CodeMasterMapper;
import com.SpringMVC.model.CodeMaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class CodeMasterDAOImpl extends JdbcDaoSupport implements CodeMasterDAO {
 
    @Autowired
    public CodeMasterDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    public void save(CodeMaster codeMaster) {
        String sql = "INSERT INTO tblCodeMaster (codetype, codeid, codename) VALUES (?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		codeMaster.getcodetype(), codeMaster.getcodeid(), codeMaster.getcodename());
    }
    
    public void update(CodeMaster codeMaster) {
        String sql = "UPDATE tblCodeMaster SET codename=? WHERE codetype=? AND codeid=?";
        this.getJdbcTemplate().update(sql, codeMaster.getcodename(), 
        		codeMaster.getcodetype(), codeMaster.getcodeid());
    }
    
    public void delete(String codeid) {
        String sql = "DELETE FROM tblCodeMaster WHERE codeid=?";
        this.getJdbcTemplate().update(sql, codeid);
    }
    
    public CodeMaster get(String codeid) {
	    String sql = "SELECT codetype, codeid, codename FROM tblCodeMaster WHERE codeid='"+codeid+"'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<CodeMaster>() {
	 
			@Override
	        public CodeMaster extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            	CodeMaster codeMaster = new CodeMaster();
	            	codeMaster.setcodetype(rs.getString("codetype"));
	            	codeMaster.setcodeid(rs.getString("codeid"));
	            	codeMaster.setcodename(rs.getString("codename"));
	                return codeMaster;
	            }	 
	            return null;
	        }
        });
    }
    
    public List<CodeMaster> list(String codetype) {
	    String sql = "SELECT codetype, codeid, codename FROM tblCodeMaster WHERE codetype='"+codetype+"'";
	    
        CodeMasterMapper mapper = new CodeMasterMapper();
        List<CodeMaster> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }                    

    @Override
    public List<String> getType(String codetype) {
        String sql = "SELECT codename FROM tblCodeMaster WHERE codetype = ? ORDER BY codeid";
         
        Object[] params = new Object[] { codetype };         
        List<String> codes = this.getJdbcTemplate().queryForList(sql,params, String.class);         
        return codes;
    }

}