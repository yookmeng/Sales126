package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
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
import com.SpringMVC.dao.CommonDAO;
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.ExcelDetail;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.MonthlySummary;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserMonthlySummary;
import com.SpringMVC.model.UserValidate;
import com.SpringMVC.uriconstant.UserRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class UserController {

	@Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private UserMonthlySummaryDAO userMonthlySummaryDAO;

	@Autowired
    private ProspectDAO prospectDAO;

	@Autowired
    private CommonDAO commonDAO;

	@RequestMapping(value = UserRestURIConstant.Get, method = RequestMethod.GET)
	public String getUserProfile(@PathVariable int userid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userLoginDAO.findUser(userid));
		} catch (JsonProcessingException e) {
		}
		return jsonInString;
	}

	@RequestMapping(value = UserRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getAllUser(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(userLoginDAO.list(userLogin.getrole(), userLogin.getcompanyid()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonInString;
	}

	@RequestMapping(value = UserRestURIConstant.GetByTeam, method = RequestMethod.POST)
	public String getAllUserByTeam(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(userLoginDAO.listByTeam(userLogin.getteamid()));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonInString;
	}

	@RequestMapping(value = UserRestURIConstant.Validate, method = RequestMethod.POST)
    public String validateUser(@RequestBody UserValidate userValidate) throws IOException {
    	UserLogin userLogin = userLoginDAO.findUserEmail(userValidate.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
    	if (userLogin == null) {
        	return jsonInString;
        }
        if (!userLogin.getpassword().equals(userValidate.getpassword())){
        	return jsonInString;
        }
		jsonInString = mapper.writeValueAsString(userLoginDAO.findUser(userLogin.getuserid()));
    	return jsonInString;
    }

	@RequestMapping(value = UserRestURIConstant.MonthlySummary, method = RequestMethod.POST)
    public String monthlySummary(@RequestBody MonthlySummary monthlySummary) throws IOException {
    	UserLogin userLogin = userLoginDAO.findUserEmail(monthlySummary.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		jsonInString = mapper.writeValueAsString(userMonthlySummaryDAO.listAll(monthlySummary.getperiod(), userLogin.getuserid(), userLogin.getrole()));
    	return jsonInString;
    }

	@RequestMapping(value = UserRestURIConstant.SendExcel, method = RequestMethod.POST)
    public String sendExcel(@RequestBody MonthlySummary monthlySummary,
    		HttpServletRequest request) throws IOException {
    	UserLogin userLogin = userLoginDAO.findUserEmail(monthlySummary.getemail());
    	List<ExcelDetail> prospect = prospectDAO.listPeriod(userLogin.getuserid(), monthlySummary.getperiod(), userLogin.getrole());
    	List<UserMonthlySummary> summary =userMonthlySummaryDAO.list(monthlySummary.getperiod(), userLogin.getuserid(), userLogin.getrole());
    	commonDAO.sendEmail(request, prospect, summary, userLogin.getuserid(), monthlySummary.getperiod());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		jsonInString = mapper.writeValueAsString(userMonthlySummaryDAO.list(monthlySummary.getperiod(), userLogin.getuserid(), userLogin.getrole()));
    	return jsonInString;
    }
	
	@RequestMapping(value = UserRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<UserLogin> createUser(@RequestBody UserLogin userLogin) throws IOException {
		if (userLoginDAO.isExist(userLogin)){
            return new ResponseEntity<UserLogin>(userLogin, HttpStatus.CONFLICT);			
		}
		
		userLoginDAO.save(userLogin);
        return new ResponseEntity<UserLogin>(userLogin, HttpStatus.CREATED);
    }

    @RequestMapping(value = UserRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<UserLogin> updateUser(@RequestBody UserLogin userLogin) {
    	UserLogin currentUserLogin = userLoginDAO.findUser(userLogin.getuserid());         
        if (currentUserLogin==null) {
            return new ResponseEntity<UserLogin>(userLogin, HttpStatus.NOT_FOUND);
        }

        //updated email shouldn't duplicate with other user
    	if (currentUserLogin.getemail().equals(userLogin.getemail())){
    	} else {
    		if (userLoginDAO.isExist(userLogin)){
                return new ResponseEntity<UserLogin>(userLogin, HttpStatus.CONFLICT);			
    		}        	
        }
        currentUserLogin.setpassword(userLogin.getpassword());
        currentUserLogin.setteamid(userLogin.getteamid());
        currentUserLogin.setbranchid(userLogin.getbranchid());
        currentUserLogin.setcompanyid(userLogin.getcompanyid());
        currentUserLogin.setmobile(userLogin.getmobile());
        currentUserLogin.setemail(userLogin.getemail());
        currentUserLogin.setimgurl(userLogin.getimgurl());
        currentUserLogin.setimgthumburl(userLogin.getimgthumburl());

        userLoginDAO.update(currentUserLogin);
        return new ResponseEntity<UserLogin>(userLogin, HttpStatus.OK);
    }

    @RequestMapping(value = UserRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<UserLogin> deleteUser(@RequestBody UserLogin userLogin) {
    	if (userLogin == null) {
            return new ResponseEntity<UserLogin>(userLogin, HttpStatus.NOT_FOUND);
        }
        userLoginDAO.delete(userLogin.getusername());
        return new ResponseEntity<UserLogin>(userLogin, HttpStatus.OK);
    }
    
	@RequestMapping(value="/listUser")
	public ModelAndView listUser(ModelAndView model, Principal principal) throws IOException{
	   UserLogin userLogin = userLoginDAO.get(principal.getName());
	   List<UserLogin> listUser = userLoginDAO.list(userLogin.getrole(), userLogin.getcompanyid());
	   model.addObject("listUser", listUser);
	   model.addObject("role", userLoginDAO.getUserRoles(principal.getName()));
	   model.setViewName("userList");	 	    
	   return model;
	}
	   
   @RequestMapping(value = "/addUser", method = RequestMethod.GET)
   public ModelAndView addUser(ModelAndView model, Principal principal) {
       UserLogin userLogin = userLoginDAO.get(principal.getName());
       UserLogin newUser = new UserLogin();
       model.addObject("user", newUser);
       if (userLogin.getrole().equals("DEV")){       
	       List<String> roles= userLoginDAO.getAllRoles();	
	       model.addObject("role", userLoginDAO.getUserRoles(principal.getName()));
	       model.addObject("rolelist", roles);
       }
	   else {		// SA
		   newUser.setcompanyid(userLogin.getcompanyid());
    	   List<String> roles= new ArrayList<String>();	
    	   roles.add("MA");
    	   roles.add("TL");
    	   model.addObject("role", userLogin.getrole());
           model.addObject("rolelist", roles);
       }
       model.setViewName("userForm");
       return model;
   }

   @RequestMapping(value = "/editUser", method = RequestMethod.GET)
   public ModelAndView editUser(HttpServletRequest request) {
       String username = request.getParameter("username");
       UserLogin editUser = userLoginDAO.get(username);
       UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
       Team team = teamDAO.get(userLogin.getteamid());
       Branch branch = branchDAO.get(userLogin.getbranchid());
       Company company = companyDAO.get(userLogin.getcompanyid());
       ModelAndView model = new ModelAndView("userForm");
       if (userLogin.getrole().equals("DEV")){
        	   List<String> roles= userLoginDAO.getAllRoles();	
               model.addObject("role", userLogin.getrole());
               model.addObject("rolelist", roles);
           }
	   else {		// Others
		   if (request.getUserPrincipal().getName().equals(username)){
			   List<String> roles= new ArrayList<String>();	
        	   roles.add(userLogin.getrole());
        	   model.addObject("role", userLogin.getrole());
               model.addObject("rolelist", roles);			   
		   }
		   else {
			   List<String> roles= new ArrayList<String>();	
        	   roles.add("MA");
        	   roles.add("TL");
        	   model.addObject("role", userLogin.getrole());
               model.addObject("rolelist", roles);
    	   }
	   }
       model.addObject("company", company);
       model.addObject("branch", branch);
       model.addObject("team", team);
       model.addObject("user", editUser);       
       return model;
   }

   @RequestMapping(value="/listMember", method = RequestMethod.GET)
   public ModelAndView listMember(HttpServletRequest request) {
       int teamid = Integer.parseInt(request.getParameter("teamid"));
       Team team = teamDAO.get(teamid);
       Branch branch = branchDAO.get(team.getbranchid());
       Company company = companyDAO.get(branch.getcompanyid());
       UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
	   List<UserLogin> member = userLoginDAO.listByTeam(teamid);
       ModelAndView mav = new ModelAndView("memberList");
       mav.addObject("role", userLogin.getrole());
       mav.addObject("company", company);
       mav.addObject("branch", branch);
       mav.addObject("team", team);
       mav.addObject("member", member);
	    return mav;
	}
   
   @RequestMapping(value = "/addMember", method = RequestMethod.GET)
   public ModelAndView addMember(HttpServletRequest request) {
       int teamid = Integer.parseInt(request.getParameter("teamid"));
       UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
       Team team = teamDAO.get(teamid);
       Branch branch = branchDAO.get(team.getbranchid());
       Company company = companyDAO.get(branch.getcompanyid());
       UserLogin newMember = new UserLogin();
       newMember.setcompanyid(branch.getcompanyid());
       newMember.setbranchid(team.getbranchid());
       newMember.setteamid(teamid);
       newMember.setrole("USER");        
       ModelAndView mav = new ModelAndView("memberForm");
       mav.addObject("company", company);
       mav.addObject("branch", branch);
       mav.addObject("team", team);
       mav.addObject("role", userLogin.getrole());
       mav.addObject("member", newMember);
       return mav;
   }
   
   @RequestMapping(value = "/editMember", method = RequestMethod.GET)
   public ModelAndView editMember(HttpServletRequest request) {
       int userid = Integer.parseInt(request.getParameter("userid"));
       UserLogin editMember = userLoginDAO.findUser(userid);
       Team team = teamDAO.get(editMember.getteamid());
       Branch branch = branchDAO.get(team.getbranchid());
       Company company = companyDAO.get(branch.getcompanyid());
       editMember.setbranchid(team.getbranchid());
       editMember.setcompanyid(branch.getcompanyid());
       UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
       ModelAndView mav = new ModelAndView("memberForm");
       mav.addObject("company", company);
       mav.addObject("branch", branch);
       mav.addObject("team", team);
       mav.addObject("role", userLogin.getrole());
       mav.addObject("member", editMember);
       return mav;
   }        
}
