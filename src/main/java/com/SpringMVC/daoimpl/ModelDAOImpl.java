	package com.SpringMVC.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
 
import javax.sql.DataSource;
 
import com.SpringMVC.model.Model;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.mapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
@Repository 
public class ModelDAOImpl extends JdbcDaoSupport implements ModelDAO { 
    @Autowired
    public ModelDAOImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
    @Override
    public List<String> getSellingModels(int brandid) {
        String sql = "Select modelname FROM tblModel "
        		+ "WHERE brandid = " + brandid + " "
        		+ "AND sellingmodel = TRUE";
        List<String> brands = this.getJdbcTemplate().queryForList(sql, String.class);         
        return brands;
    }

    @Override
    public List<String> getAllModels(int brandid) {
        String sql = "Select modelname FROM tblModel WHERE brandid = " + brandid;         
        List<String> brands = this.getJdbcTemplate().queryForList(sql, String.class);         
        return brands;
    }

    public void save(Model model) {
        // insert
        String sql = "INSERT INTO tblModel ("
        		+ "brandid, modelname, sellingmodel, commission, price, suminsured, premium, roadtax, "
        		+ "colour, enginetype, fuelsupplysystem, displacement, maxpower, maxtorque, transmission) "
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        this.getJdbcTemplate().update(sql, 
        		model.getbrandid(), model.getmodelname(), model.getsellingmodel(),
        		model.getcommission(), model.getprice(), model.getsuminsured(), model.getpremium(),
        		model.getroadtax(), model.getcolour(), model.getenginetype(), model.getfuelsupplysystem(), 
        		model.getdisplacement(), model.getmaxpower(), model.getmaxtorque(), model.gettransmission());
    }
    
    public void update(Model model) {
        // update
        String sql = "UPDATE tblModel SET modelname=?, sellingmodel=?, commission=?, price=?, "
        		+ "suminsured=?, premium=?, roadtax=?, colour=?, enginetype=?, fuelsupplysystem=?, "
        		+ "displacement=?, maxpower=?, maxtorque=?, transmission=? WHERE modelid=?";
        this.getJdbcTemplate().update(sql, 
        		model.getmodelname(), model.getsellingmodel(), model.getcommission(), 
        		model.getprice(), model.getsuminsured(), model.getpremium(), model.getroadtax(), 
        		model.getcolour(), model.getenginetype(), model.getfuelsupplysystem(), 
        		model.getdisplacement(), model.getmaxpower(), model.getmaxtorque(), 
        		model.gettransmission(), model.getmodelid());
    }

    public void delete(int modelid) {
        String sql = "DELETE FROM tblModel WHERE modelid=?";
        this.getJdbcTemplate().update(sql, modelid);
    }
    
    public List<Model> list(int companyid) {
        String sql = "SELECT * FROM tblModel m "
        		+ "LEFT JOIN tblBrand b "
        		+ "ON m.brandid = b.brandid "
        		+ "WHERE b.companyid = " + companyid;
        ModelMapper mapper = new ModelMapper();
        List<Model> list = this.getJdbcTemplate().query(sql, mapper);
        return list;
    }

    public Model get(int modelid) {
	    String sql = "SELECT * FROM tblModel WHERE modelid=" + modelid;
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Model>() {
	 
	        @Override
	        public Model extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Model model = new Model();
	                model.setmodelid(rs.getInt("modelid"));
	                model.setmodelname(rs.getString("modelname"));
	                model.setbrandid(rs.getInt("brandid"));
	                model.setsellingmodel(rs.getBoolean("sellingmodel"));
	                model.setcommission(rs.getFloat("commission"));
	                model.setprice(rs.getFloat("price"));
	                model.setsuminsured(rs.getFloat("suminsured"));
	                model.setpremium(rs.getFloat("premium"));
	                model.setroadtax(rs.getFloat("roadtax"));
	                model.setcolour(rs.getString("colour"));
	                model.setenginetype(rs.getString("enginetype"));
	                model.setfuelsupplysystem(rs.getString("fuelsupplysystem"));
	                model.setdisplacement(rs.getString("displacement"));
	                model.setmaxpower(rs.getString("maxpower"));
	                model.setmaxtorque(rs.getString("maxtorque"));
	                model.settransmission(rs.getString("transmission"));
	                return model;
	            }	 
	            return null;
	        }
        });
    }

    public Model getByName(String modelname) {
	    String sql = "SELECT * FROM tblModel WHERE modelname='" + modelname + "'";
	    return this.getJdbcTemplate().query(sql, new ResultSetExtractor<Model>() {
	 
	        @Override
	        public Model extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	                Model model = new Model();
	                model.setmodelid(rs.getInt("modelid"));
	                model.setmodelname(rs.getString("modelname"));
	                model.setbrandid(rs.getInt("brandid"));
	                model.setsellingmodel(rs.getBoolean("sellingmodel"));
	                model.setcommission(rs.getFloat("commission"));
	                model.setprice(rs.getFloat("price"));
	                model.setsuminsured(rs.getFloat("suminsured"));
	                model.setpremium(rs.getFloat("premium"));
	                model.setroadtax(rs.getFloat("roadtax"));
	                model.setcolour(rs.getString("colour"));
	                model.setenginetype(rs.getString("enginetype"));
	                model.setfuelsupplysystem(rs.getString("fuelsupplysystem"));
	                model.setdisplacement(rs.getString("displacement"));
	                model.setmaxpower(rs.getString("maxpower"));
	                model.setmaxtorque(rs.getString("maxtorque"));
	                model.settransmission(rs.getString("transmission"));	                
	                return model;
	            }	 
	            return null;
	        }
        });
    }

    public boolean isExist(Model model) {
        return getByName(model.getmodelname())!=null;
    }
}