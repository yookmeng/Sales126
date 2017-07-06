package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
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

import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.BranchTargetDAO;
import com.SpringMVC.dao.ClosingPeriodDAO;
import com.SpringMVC.dao.CompanyTargetDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.BranchTarget;
import com.SpringMVC.model.CompanyTarget;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.BranchTargetRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class BranchTargetController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ClosingPeriodDAO closingPeriodDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private CompanyTargetDAO companyTargetDAO;

    @Autowired
    private BranchTargetDAO branchTargetDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = BranchTargetRestURIConstant.Get, method = RequestMethod.GET)
	public String getBranchTarget(@PathVariable int targetid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(branchTargetDAO.get(targetid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = BranchTargetRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getAllBranchTarget(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
				case MD:
			    	int companyid = userLogin.getcompanyid();
					jsonInString = mapper.writeValueAsString(branchTargetDAO.listByCompany(companyid));
					break;    	   
				case MA:
			    	int branchid = userLogin.getbranchid();
					jsonInString = mapper.writeValueAsString(branchTargetDAO.listByBranch(branchid));
					break;
				default:
					break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = BranchTargetRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<BranchTarget> createBranchTarget(@RequestBody BranchTarget branchTarget) throws IOException {
    	Branch branch = branchDAO.getByName(branchTarget.getbranchname());
    	branchTarget.setbranchid(branch.getbranchid());
    	branchTargetDAO.save(branchTarget);
        return new ResponseEntity<BranchTarget>(branchTarget, HttpStatus.CREATED);
    }

    @RequestMapping(value = BranchTargetRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<BranchTarget> updateBranchTarget(@RequestBody BranchTarget branchTarget) {
    	BranchTarget currentBranchTarget = branchTargetDAO.get(branchTarget.gettargetid());         
        if (currentBranchTarget==null) {
            return new ResponseEntity<BranchTarget>(branchTarget, HttpStatus.NOT_FOUND);
        }

        currentBranchTarget.setprospect(branchTarget.getprospect());
        currentBranchTarget.settestdrive(branchTarget.gettestdrive());
        currentBranchTarget.setclosed(branchTarget.getclosed());

        branchTargetDAO.update(currentBranchTarget);
        return new ResponseEntity<BranchTarget>(branchTarget, HttpStatus.OK);
    }

    @RequestMapping(value = BranchTargetRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<BranchTarget> deleteBranchTarget(@RequestBody BranchTarget branchTarget) {
        if (branchTarget == null) {
            return new ResponseEntity<BranchTarget>(branchTarget, HttpStatus.NOT_FOUND);
        }
 
        branchTargetDAO.delete(branchTarget.gettargetid());
        return new ResponseEntity<BranchTarget>(branchTarget, HttpStatus.OK);
    }
    
    @RequestMapping(value="/listBranchTarget", method = RequestMethod.GET)
    public ModelAndView listBranchTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        CompanyTarget companyTarget = companyTargetDAO.get(targetid);
 	    List<BranchTarget> listBranchTarget = branchTargetDAO.list(companyTarget.getperiod(), companyTarget.getcompanyid());
        ModelAndView mav = new ModelAndView("branchTargetList");
        mav.addObject("companyTarget", companyTarget);
 	    mav.addObject("listTarget", listBranchTarget);
 	    return mav;
 	}
 	   
    @RequestMapping(value="/listBranchTargetMA", method = RequestMethod.GET)
    public ModelAndView listBranchTargetMA(Principal principal) {
        UserLogin userLogin = userLoginDAO.get(principal.getName());
        Branch branch = branchDAO.getByMA(userLogin.getuserid());
 	    List<BranchTarget> listBranchTarget = branchTargetDAO.listByBranch(branch.getbranchid());
        List<String> periods = closingPeriodDAO.getPeriod(userLogin.getcompanyid());	
        ModelAndView mav = new ModelAndView("branchTargetMAList");
 	    mav.addObject("branch", branch);
 	    mav.addObject("periods", periods);
 	    mav.addObject("listTarget", listBranchTarget);
 	    return mav;
 	}

    @RequestMapping(value = "/addBranchTarget", method = RequestMethod.GET)
    public ModelAndView addBranchTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        CompanyTarget companyTarget = companyTargetDAO.get(targetid);
        BranchTarget newBranchTarget = new BranchTarget();
        newBranchTarget.setperiod(companyTarget.getperiod());
        newBranchTarget.setcompanytargetid(companyTarget.gettargetid());        
        ModelAndView mav = new ModelAndView("branchTargetForm");
        List<String> branches = branchDAO.branchList(companyTarget.getcompanyid());	
        mav.addObject("branchlist", branches);
        mav.addObject("companyTarget", companyTarget);
        mav.addObject("branchTarget", newBranchTarget);
        return mav;
    }

    @RequestMapping(value = "/editBranchTarget", method = RequestMethod.GET)
    public ModelAndView editBranchTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        BranchTarget editBranchTarget = branchTargetDAO.get(targetid);
        CompanyTarget companyTarget = companyTargetDAO.get(editBranchTarget.getcompanytargetid());
        ModelAndView mav = new ModelAndView("branchTargetForm");
        List<String> branches = branchDAO.branchList(companyTarget.getcompanyid());	
        mav.addObject("branchlist", branches);
        mav.addObject("companyTarget", companyTarget);
        mav.addObject("branchTarget", editBranchTarget);
        return mav;
    }       

}
