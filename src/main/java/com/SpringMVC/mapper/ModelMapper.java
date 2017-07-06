package com.SpringMVC.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Model;
import org.springframework.jdbc.core.RowMapper;
 
public class ModelMapper implements RowMapper<Model> {
 
    @Override
    public Model mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int modelid = rs.getInt("modelid");
        String modelname = rs.getString("modelname");
        int brandid = rs.getInt("brandid");
        boolean sellingmodel = rs.getBoolean("sellingmodel"); 
        float commission = rs.getFloat("commission");
        float price = rs.getFloat("price");
        float suminsured = rs.getFloat("suminsured");
        float premium = rs.getFloat("premium");        
        float roadtax = rs.getFloat("roadtax");        
        String colour = rs.getString("colour");
        String enginetype = rs.getString("enginetype");
        String fuelsupplysystem = rs.getString("fuelsupplysystem");
        String displacement = rs.getString("displacement");
        String maxpower = rs.getString("maxpower");
        String maxtorque = rs.getString("maxtorque");
        String transmission = rs.getString("transmission");

        return new Model(modelid, modelname, brandid, sellingmodel, commission, price,
        		suminsured, premium, roadtax, colour, enginetype, fuelsupplysystem, 
        		displacement, maxpower, maxtorque, transmission);
        
    }
}