package com.SpringMVC.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.SpringMVC.model.Quotation;
import org.springframework.jdbc.core.RowMapper;
 
public class QuotationMapper implements RowMapper<Quotation> {
 
    @Override
    public Quotation mapRow(ResultSet rs, int rowNum) throws SQLException { 
        int quotationid = rs.getInt("quotationid");
        Date quotationdate = rs.getDate("quotationdate");
        int prospectid = rs.getInt("prospectid");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        int activityid = rs.getInt("activityid");
        int brandid = rs.getInt("brandid");
        String brandname = rs.getString("brandname");
        int modelid = rs.getInt("modelid");
        String modelname = rs.getString("modelname");
        String colour = rs.getString("colour");
        float retailprice = rs.getFloat("retailprice");
        float suminsured = rs.getFloat("suminsured");
        String ncd = rs.getString("ncd");
        float premium = rs.getFloat("premium");
        float premiumafterncd = rs.getFloat("premiumafterncd");
        float roadtax = rs.getFloat("roadtax");
        float registrationfee = rs.getFloat("registrationfee");
        float handlingcharges = rs.getFloat("handlingcharges");
        float extendedwarranty = rs.getFloat("extendedwarranty");
        float othercharges = rs.getFloat("othercharges");
        float discount = rs.getFloat("discount");
        float quoteamount = rs.getFloat("quoteamount");
        String term = rs.getString("term");
        String remark = rs.getString("remark");
        String quotationpdflink = rs.getString("quotationpdflink");

        return new Quotation(quotationid, quotationdate, prospectid, firstname, lastname,
        		activityid, brandid, brandname, modelid, modelname, colour,
        		retailprice, suminsured, ncd, premium, premiumafterncd, roadtax,
        		registrationfee, handlingcharges, extendedwarranty,
        		othercharges, discount, quoteamount, term, remark,
        		quotationpdflink);        
    }
}