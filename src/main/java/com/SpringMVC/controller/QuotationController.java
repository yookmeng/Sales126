package com.SpringMVC.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.SpringMVC.dao.ActivityDAO;
import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.CodeMasterDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.QuotationDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Activity;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.Quotation;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.QuotationRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class QuotationController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ActivityDAO activityDAO;

    @Autowired
    private ProspectDAO prospectDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private CodeMasterDAO codeMasterDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private QuotationDAO quotationDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = QuotationRestURIConstant.Get, method = RequestMethod.GET)
	public String getQuotation(@PathVariable int quotationid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(quotationDAO.get(quotationid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = QuotationRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getQuotationByProspect(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
			case USER:
				jsonInString = mapper.writeValueAsString(quotationDAO.listByUser(userLogin.getuserid()));
				break;    	   
			case TL:
				Team team = teamDAO.getByUser(userLogin.getuserid());
				jsonInString = mapper.writeValueAsString(quotationDAO.listByTeam(team.getteamid()));
				break;    	   
			case MA:
				Branch branch = branchDAO.getByMA(userLogin.getuserid());
				jsonInString = mapper.writeValueAsString(quotationDAO.listByBranch(branch.getbranchid()));
				break;    	   
			case MD:
				jsonInString = mapper.writeValueAsString(quotationDAO.listByCompany(userLogin.getcompanyid()));
				break;
			default:
				break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = QuotationRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Quotation> createQuotation(@RequestBody Quotation quotation, 
    		HttpServletRequest request) throws IOException {
    	quotationDAO.save(quotation);
        int quotationid = quotationDAO.getlastquotationid(quotation.getprospectid());
        Quotation newQuotation = quotationDAO.get(quotationid);
    	quotationDAO.createpdf(newQuotation, request);
        return new ResponseEntity<Quotation>(quotation, HttpStatus.CREATED);
    }

    @RequestMapping(value = QuotationRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Quotation> updateQuotation(@RequestBody Quotation quotation, 
    		HttpServletRequest request) {
    	Quotation currentQuotation = quotationDAO.get(quotation.getquotationid());
         
        if (currentQuotation==null) {
            return new ResponseEntity<Quotation>(HttpStatus.NOT_FOUND);
        }
        
    	currentQuotation.setquotationdate(quotation.getquotationdate());
    	currentQuotation.setprospectid(quotation.getprospectid());
    	currentQuotation.setactivityid(quotation.getactivityid());
        currentQuotation.setbrandid(quotation.getbrandid());
        currentQuotation.setmodelid(quotation.getmodelid());
        currentQuotation.setcolour(quotation.getcolour());
        currentQuotation.setretailprice(quotation.getretailprice());
        currentQuotation.setsuminsured(quotation.getsuminsured());
        currentQuotation.setncd(quotation.getncd());
        currentQuotation.setpremium(quotation.getpremium());
        currentQuotation.setroadtax(quotation.getroadtax());
        currentQuotation.setregistrationfee(quotation.getregistrationfee());
        currentQuotation.sethandlingcharges(quotation.gethandlingcharges());
        currentQuotation.setextendedwarranty(quotation.getextendedwarranty());
        currentQuotation.setothercharges(quotation.getothercharges());
        currentQuotation.setdiscount(quotation.getdiscount());
        currentQuotation.setquoteamount(quotation.getquoteamount());
        currentQuotation.setterm(quotation.getterm());
        currentQuotation.setremark(quotation.getremark());

        quotationDAO.update(currentQuotation);
    	quotationDAO.createpdf(currentQuotation, request);
        return new ResponseEntity<Quotation>(quotation, HttpStatus.OK);
    }

    @RequestMapping(value = QuotationRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Quotation> deleteQuotation(@RequestBody Quotation quotation) {
        if (quotation == null) {
            return new ResponseEntity<Quotation>(quotation, HttpStatus.NOT_FOUND);
        }
 
        quotationDAO.delete(quotation.getquotationid());
        return new ResponseEntity<Quotation>(quotation, HttpStatus.OK);
    }

    @RequestMapping(value="/listQuotation", method = RequestMethod.GET)
    public ModelAndView listRequest(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Prospect prospect = prospectDAO.get(prospectid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
 	    List<Quotation> listQuotation = quotationDAO.list(prospectid);
        ModelAndView mav = new ModelAndView("quotationList");
        mav.addObject("role", userLogin.getrole());        
        mav.addObject("prospect", prospect);
 	    mav.addObject("listQuotation", listQuotation);
 	    return mav;
 	}
 	   
    @RequestMapping(value = "/addQuotation", method = RequestMethod.GET)
    public ModelAndView addQuotation(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        int activityid = activityDAO.getlastactivityid(prospectid);
        Activity activity = activityDAO.get(activityid);
        Prospect prospect = prospectDAO.get(activity.getprospectid());
        Model model = modelDAO.get(activity.getmodelid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Quotation newQuotation = new Quotation();
        newQuotation.setquotationdate(activity.getactivitydate());
        newQuotation.setprospectid(activity.getprospectid());
        newQuotation.setactivityid(activity.getactivityid());
        newQuotation.setbrandid(activity.getbrandid());
        newQuotation.setmodelid(activity.getmodelid());
        newQuotation.setretailprice(model.getprice());
        newQuotation.setsuminsured(model.getsuminsured());
        newQuotation.setpremium(model.getpremium());
        newQuotation.setroadtax(model.getroadtax());
        newQuotation.setcolour(model.getcolour());
        ModelAndView mav = new ModelAndView("quotationForm");
        List<String> ncds = codeMasterDAO.getType("NCD");	
        mav.addObject("role", userLogin.getrole());
        mav.addObject("prospect", prospect);
        mav.addObject("activity", activity);
        mav.addObject("ncdlist", ncds);
        mav.addObject("quotation", newQuotation);
        return mav;
    }
            
    @RequestMapping(value = "/editQuotation", method = RequestMethod.GET)
    public ModelAndView editQuotation(HttpServletRequest request) {
        int quotationid = Integer.parseInt(request.getParameter("quotationid"));       	
        Quotation editQuotation = quotationDAO.get(quotationid);
        Activity activity = activityDAO.get(editQuotation.getactivityid());
        Prospect prospect = prospectDAO.get(activity.getprospectid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("quotationForm");
        List<String> ncds = codeMasterDAO.getType("NCD");	
 	   	mav.addObject("role", userLogin.getrole());
        mav.addObject("prospect", prospect);
        mav.addObject("activity", activity);
        mav.addObject("ncdlist", ncds);
        mav.addObject("quotation", editQuotation);
        return mav;
    }
}
