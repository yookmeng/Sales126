package com.SpringMVC.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import com.SpringMVC.model.Quotation;
@Repository 
public interface QuotationDAO {
    public void save(Quotation quotation);

    public void update(Quotation quotation);
     
    public void delete(int quotationid);
     
    public void createpdf(Quotation quotation, HttpServletRequest request);

    public Quotation get(int quotationid);
         
    public List<Quotation> list(int prospectid);    

    public List<Quotation> listByUser(int userid);    

    public List<Quotation> listByTeam(int teamid);    

    public List<Quotation> listByBranch(int branchid);    

    public List<Quotation> listByCompany(int companyid);    

    public int getlastquotationid(int prospectid);
}