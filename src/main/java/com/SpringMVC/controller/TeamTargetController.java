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

import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.BranchTargetDAO;
import com.SpringMVC.dao.ClosingPeriodDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.exceptions.ServiceException;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.BranchTarget;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.TeamTarget;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.TeamTargetRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class TeamTargetController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ClosingPeriodDAO closingPeriodDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private BranchTargetDAO branchTargetDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private TeamTargetDAO teamTargetDAO;

    private enum Roles {
    	USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = TeamTargetRestURIConstant.Get, method = RequestMethod.GET)
	public String getTeamTarget(@PathVariable int targetid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(teamTargetDAO.get(targetid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = TeamTargetRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getAllTeamTargetByPeriodTeam(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
				case MD:
			    	int companyid = userLogin.getcompanyid();
					jsonInString = mapper.writeValueAsString(teamTargetDAO.listByCompany(companyid));
					break;    	   
				case MA:
			    	int branchid = userLogin.getbranchid();
					jsonInString = mapper.writeValueAsString(teamTargetDAO.listByBranch(branchid));
					break;
				case TL:
			    	int teamid = userLogin.getteamid();
					jsonInString = mapper.writeValueAsString(teamTargetDAO.listByTeam(teamid));
					break;
				default:
					break;	
			}			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = TeamTargetRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<TeamTarget> createTeamTarget(@RequestBody TeamTarget teamTarget) throws IOException {
    	Team team = teamDAO.getByName(teamTarget.getteamname());
    	teamTarget.setteamid(team.getteamid());
    	teamTargetDAO.save(teamTarget);
        return new ResponseEntity<TeamTarget>(teamTarget, HttpStatus.CREATED);
    }

    @RequestMapping(value = TeamTargetRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<TeamTarget> updateTeamTarget(@RequestBody TeamTarget teamTarget) {
    	TeamTarget currentTeamTarget = teamTargetDAO.get(teamTarget.gettargetid());         
        if (currentTeamTarget==null) {
            return new ResponseEntity<TeamTarget>(teamTarget, HttpStatus.NOT_FOUND);
        }

        currentTeamTarget.setprospect(teamTarget.getprospect());
        currentTeamTarget.settestdrive(teamTarget.gettestdrive());
        currentTeamTarget.setclosed(teamTarget.getclosed());

        teamTargetDAO.update(currentTeamTarget);
        return new ResponseEntity<TeamTarget>(teamTarget, HttpStatus.OK);
    }

    @RequestMapping(value = TeamTargetRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<TeamTarget> deleteTeamTarget(@RequestBody TeamTarget teamTarget) {
        if (teamTarget == null) {
            return new ResponseEntity<TeamTarget>(teamTarget, HttpStatus.NOT_FOUND);
        }
 
        teamTargetDAO.delete(teamTarget.gettargetid());
        return new ResponseEntity<TeamTarget>(teamTarget, HttpStatus.OK);
    }

    @RequestMapping(value="/listTeamTarget", method = RequestMethod.GET)
    public ModelAndView listTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        BranchTarget branchTarget = branchTargetDAO.get(targetid);
        Branch branch = branchDAO.get(branchTarget.getbranchid());
 	    List<TeamTarget> listTeamTarget = teamTargetDAO.list(branchTarget.getperiod(), branchTarget.getbranchid());
        ModelAndView mav = new ModelAndView("teamTargetList");
        mav.addObject("branch", branch);
        mav.addObject("branchTarget", branchTarget);
 	    mav.addObject("listTarget", listTeamTarget);
 	    return mav;
 	}
 	   
    @RequestMapping(value="/listTeamTargetTL", method = RequestMethod.GET)
    public ModelAndView listTeamTargetUser(HttpServletRequest request) {
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Team team = teamDAO.getByUser(userLogin.getuserid());
        List<String> periods = closingPeriodDAO.getPeriod(userLogin.getcompanyid());	
        if (userLogin.getuserid() == team.getleaderid()){
	 	    List<TeamTarget> listTeamTarget = teamTargetDAO.listByTeam(team.getteamid());
	 	    ModelAndView mav = new ModelAndView("teamTargetTLList");
	 	    mav.addObject("periods", periods);
	        mav.addObject("team", team);
	 	    mav.addObject("listTarget", listTeamTarget);
	 	    return mav;
        }    	
	    else {
        	throw new ServiceException(HttpStatus.NOT_FOUND, "You have no access right!");
	    }
    }
    
    @RequestMapping(value = "/addTeamTarget", method = RequestMethod.GET)
    public ModelAndView addTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        BranchTarget branchTarget = branchTargetDAO.get(targetid);
        Branch branch = branchDAO.get(branchTarget.getbranchid());
        TeamTarget newTeamTarget = new TeamTarget();
        newTeamTarget.setperiod(branchTarget.getperiod());
        newTeamTarget.setbranchtargetid(branchTarget.gettargetid());        
        ModelAndView mav = new ModelAndView("teamTargetForm");
        List<String> teams = teamDAO.teamList(branchTarget.getbranchid());	
        mav.addObject("teamlist", teams);
        mav.addObject("branch", branch);
        mav.addObject("branchTarget", branchTarget);
        mav.addObject("teamTarget", newTeamTarget);
        return mav;
    }

    @RequestMapping(value = "/editTeamTarget", method = RequestMethod.GET)
    public ModelAndView editTeamTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        TeamTarget editTeamTarget = teamTargetDAO.get(targetid);
        BranchTarget branchTarget = branchTargetDAO.get(editTeamTarget.getbranchtargetid());
        Branch branch = branchDAO.get(branchTarget.getbranchid());
        ModelAndView mav = new ModelAndView("teamTargetForm");
        List<String> teams = teamDAO.teamList(branchTarget.getbranchid());	
        mav.addObject("teamlist", teams);
        mav.addObject("branch", branch);
        mav.addObject("branchTarget", branchTarget);
        mav.addObject("teamTarget", editTeamTarget);
        return mav;
    }   
}
