package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.SpringMVC.model.Branch;
import com.SpringMVC.model.BranchTarget;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.CompanyTarget;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.TeamTarget;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.BranchTargetDAO;
import com.SpringMVC.dao.ClosingPeriodDAO;
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.CompanyTargetDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.UserMonthlySummary;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
 
@Controller
public class MainController {
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
    private ClosingPeriodDAO closingPeriodDAO;

    @Autowired
    private UserMonthlySummaryDAO userMonthlySummaryDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }
    
    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(
    		@RequestParam(value = "error", required = false) String error,
    		@RequestParam(value = "logout", required = false) String logout,
    		HttpServletRequest request) {
    	ModelAndView model = new ModelAndView();
    	if (error != null) {
    		model.addObject("error", "Invalid username and password!");
    		//login form for update page
    		//if login error, get the targetUrl from session again.
    		String targetUrl = getRememberMeTargetUrlFromSession(request);
    		if(StringUtils.hasText(targetUrl)){
    			model.addObject("targetUrl", targetUrl);
    			model.addObject("loginUpdate", true);
    		}
    	}

    	if (logout != null) {
    		model.addObject("msg", "You've been logged out successfully.");
    	}
    	
    	if (SecurityContextHolder.getContext().getAuthentication() != null &&
    			 SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
    			 //when Anonymous Authentication is enabled
    			 !(SecurityContextHolder.getContext().getAuthentication() 
    			          instanceof AnonymousAuthenticationToken)) {
			model.addObject("username", request.getUserPrincipal().getName());
			UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
			String period = closingPeriodDAO.getCurrentPeriod(userLogin.getcompanyid());
			model.addObject("role", userLogin.getrole());
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
			case USER:
				UserMonthlySummary userMonthlySummary = userMonthlySummaryDAO.get(period, userLogin.getuserid(), userLogin.getrole());
				model.addObject("userMonthlySummary", userMonthlySummary);    	   
	    		model.setViewName("userDashBoard");
	    		break;
			case TL:
				Team team = teamDAO.getByUser(userLogin.getuserid());
				TeamTarget teamTarget = teamTargetDAO.getByPeriod(period, team.getteamid());
				model.addObject("listUserMonthlySummary", userMonthlySummaryDAO.list(period, userLogin.getuserid(), userLogin.getrole()));    	   
				model.addObject("team", team);
				model.addObject("teamTarget", teamTarget);    	   
	    		model.setViewName("tlDashBoard");    		    		
	    		break;
			case MA:
				Branch branch = branchDAO.getByMA(userLogin.getuserid());
				BranchTarget branchTarget = branchTargetDAO.getByPeriod(period, branch.getbranchid());
				model.addObject("listUserMonthlySummary", userMonthlySummaryDAO.list(period, userLogin.getuserid(), userLogin.getrole()));    	   
				model.addObject("branch", branch);
				model.addObject("teamTarget", teamTargetDAO.list(period, branch.getbranchid()));
				model.addObject("branchTarget", branchTarget);
	    		model.setViewName("maDashBoard");    		    		
	    		break;
			case MD:
				Company company = companyDAO.get(userLogin.getcompanyid());
				CompanyTarget companyTarget = companyTargetDAO.getByPeriod(period, company.getcompanyid());
				model.addObject("listUserMonthlySummary", userMonthlySummaryDAO.list(period, userLogin.getuserid(), userLogin.getrole()));    	   
				model.addObject("company", company);
				model.addObject("teamTarget", teamTargetDAO.listAll(period, company.getcompanyid()));
				model.addObject("branchTarget", branchTargetDAO.list(period, company.getcompanyid()));
				model.addObject("companyTarget", companyTarget);
	    		model.setViewName("mdDashBoard");    		    		
			case SA:
	    		model.setViewName("saDashBoard");    		    		
	    		break;
			default:
	    		model.setViewName("home");    		    		
	    		break;
			}
    	}
    	else {
    		model.setViewName("login");    		    		
    	}
    	return model;
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) throws ParseException {
		if (isRememberMeAuthenticated()) {
			//send login for update
			setRememberMeTargetUrlToSession(request);
			model.addAttribute("loginUpdate", true);
			return "/login";

		} else {
			model.addAttribute("username", request.getUserPrincipal().getName());
			UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
			String period = closingPeriodDAO.getCurrentPeriod(userLogin.getcompanyid());
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

   @RequestMapping(value = "/calendar")
	public ModelAndView calendar(ModelAndView model, Principal principal) throws IOException{
	   UserLogin userLogin = userLoginDAO.get(principal.getName());
	   model.addObject("userid", userLogin.getuserid());
	   model.setViewName("calendar");	 	    
	   return model;
	}
 
   @RequestMapping(value = "/403", method = RequestMethod.GET)
   public String accessDenied(Model model, Principal principal) {
        
       if (principal != null) {
           model.addAttribute("message", "Hi " + principal.getName()
                   + "<br> You do not have permission to access this page!");
       } else {
           model.addAttribute("msg",
                   "You do not have permission to access this page!");
       }
       return "403";
   }

   /**
	 * Check if user is login by remember me cookie, refer
	 * org.springframework.security.authentication.AuthenticationTrustResolverImpl
	 */
   private boolean isRememberMeAuthenticated() {
		Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}

		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute("targetUrl", "/home");
		}
	}

	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?""
                             :session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}
}