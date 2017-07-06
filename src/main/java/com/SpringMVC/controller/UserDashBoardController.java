package com.SpringMVC.controller;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.BranchTargetDAO;
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.CompanyTargetDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.BranchTarget;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.CompanyTarget;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.TeamTarget;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserMonthlySummary;

@EnableWebMvc
@RestController
public class UserDashBoardController {
    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private CompanyTargetDAO companyTargetDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private BranchTargetDAO branchTargetDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private TeamTargetDAO teamTargetDAO;

    @Autowired
    private UserMonthlySummaryDAO userMonthlySummaryDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }
    
	@RequestMapping(value = "/userDashBoard", method = RequestMethod.GET)
	public String userDashBoard(Model model, HttpServletRequest request) throws ParseException {
       String period = request.getParameter("period");
	   model.addAttribute("username", request.getUserPrincipal().getName());
	   UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
	   model.addAttribute("role", userLogin.getrole());
	   Roles role = Roles.valueOf(userLogin.getrole()); 
       switch (role){
       case USER:
    	   UserMonthlySummary userMonthlySummary = userMonthlySummaryDAO.get(period, userLogin.getuserid(), userLogin.getrole());
    	   model.addAttribute("userMonthlySummary", userMonthlySummary);    	   
    	   return "userDashBoard";    	   
       case TL:
    	   Team team = teamDAO.getByUser(userLogin.getuserid());
    	   TeamTarget teamTarget = teamTargetDAO.getByPeriod(period, team.getteamid());
    	   model.addAttribute("listUserMonthlySummary", userMonthlySummaryDAO.list(period, userLogin.getuserid(), userLogin.getrole()));    	   
    	   model.addAttribute("team", team);
    	   model.addAttribute("teamTarget", teamTarget);    	   
           return "tlDashBoard";    	   
       case MA:
    	   Branch branch = branchDAO.getByMA(userLogin.getuserid());
    	   BranchTarget branchTarget = branchTargetDAO.getByPeriod(period, branch.getbranchid());
    	   model.addAttribute("listUserMonthlySummary", userMonthlySummaryDAO.list(period, userLogin.getuserid(), userLogin.getrole()));    	   
    	   model.addAttribute("branch", branch);
    	   model.addAttribute("teamTarget", teamTargetDAO.list(period, branch.getbranchid()));
    	   model.addAttribute("branchTarget", branchTarget);
           return "maDashBoard";    	   
       case MD:
    	   Company company = companyDAO.get(userLogin.getcompanyid());
    	   CompanyTarget companyTarget = companyTargetDAO.getByPeriod(period, company.getcompanyid());
    	   model.addAttribute("listUserMonthlySummary", userMonthlySummaryDAO.list(period, userLogin.getuserid(), userLogin.getrole()));    	   
    	   model.addAttribute("company", company);
    	   model.addAttribute("teamTarget", teamTargetDAO.listAll(period, company.getcompanyid()));
    	   model.addAttribute("branchTarget", branchTargetDAO.list(period, company.getcompanyid()));
    	   model.addAttribute("companyTarget", companyTarget);
           return "mdDashBoard";    	   
       case SA:
           return "saDashBoard";    	   
       default:
    	   return "home";
       }
   }
}
