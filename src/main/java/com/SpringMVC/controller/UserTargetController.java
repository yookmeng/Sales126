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

import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.TeamTargetDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserTargetDAO;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.TeamTarget;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserTarget;
import com.SpringMVC.uriconstant.UserTargetRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class UserTargetController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private TeamTargetDAO teamTargetDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private UserTargetDAO userTargetDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = UserTargetRestURIConstant.Get, method = RequestMethod.GET)
	public String getUserTarget(@PathVariable int targetid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(userTargetDAO.get(targetid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = UserTargetRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getAllUserTargetByPeriodMember(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
				case MD:
					jsonInString = mapper.writeValueAsString(userTargetDAO.listByCompany(userLogin.getcompanyid()));
					break;    	   
				case MA:
					jsonInString = mapper.writeValueAsString(userTargetDAO.listByBranch(userLogin.getbranchid()));
					break;
				case TL:
					jsonInString = mapper.writeValueAsString(userTargetDAO.listByTeam(userLogin.getteamid()));
					break;
				case USER:
					jsonInString = mapper.writeValueAsString(userTargetDAO.listByUser(userLogin.getuserid()));
					break;
				default:
					break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = UserTargetRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<UserTarget> createUserTarget(@RequestBody UserTarget userTarget) throws IOException {
    	UserLogin userLogin = userLoginDAO.get(userTarget.getusername());
    	userTarget.setuserid(userLogin.getuserid());
    	userTargetDAO.save(userTarget);
        return new ResponseEntity<UserTarget>(userTarget, HttpStatus.CREATED);
    }

    @RequestMapping(value = UserTargetRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<UserTarget> updateUserTarget(@RequestBody UserTarget userTarget) {
    	UserTarget currentUserTarget = userTargetDAO.get(userTarget.gettargetid());         
        if (currentUserTarget==null) {
            return new ResponseEntity<UserTarget>(userTarget, HttpStatus.NOT_FOUND);
        }

        currentUserTarget.setprospect(userTarget.getprospect());
        currentUserTarget.settestdrive(userTarget.gettestdrive());
        currentUserTarget.setclosed(userTarget.getclosed());

        userTargetDAO.update(currentUserTarget);
        return new ResponseEntity<UserTarget>(userTarget, HttpStatus.OK);
    }

    @RequestMapping(value = UserTargetRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<UserTarget> deleteUserTarget(@RequestBody UserTarget userTarget) {
        if (userTarget == null) {
            return new ResponseEntity<UserTarget>(userTarget, HttpStatus.NOT_FOUND);
        } 
        userTargetDAO.delete(userTarget.gettargetid());
        return new ResponseEntity<UserTarget>(userTarget, HttpStatus.OK);
    }
    
    @RequestMapping(value="/listUserTarget", method = RequestMethod.GET)
    public ModelAndView listUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        TeamTarget teamTarget = teamTargetDAO.get(targetid);
        Team team = teamDAO.get(teamTarget.getteamid());
 	    List<UserTarget> listUserTarget = userTargetDAO.list(teamTarget.getperiod(), teamTarget.getteamid());
        ModelAndView mav = new ModelAndView("userTargetList");
        mav.addObject("team", team);
        mav.addObject("teamTarget", teamTarget);
 	    mav.addObject("listTarget", listUserTarget);
 	    return mav;
 	}

    @RequestMapping(value = "/addUserTarget", method = RequestMethod.GET)
    public ModelAndView addUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        TeamTarget teamTarget = teamTargetDAO.get(targetid);
        Team team = teamDAO.get(teamTarget.getteamid());
        UserTarget newUserTarget = new UserTarget();
        newUserTarget.setperiod(teamTarget.getperiod());
        newUserTarget.setteamtargetid(teamTarget.gettargetid());        
        ModelAndView mav = new ModelAndView("userTargetForm");
        List<String> members = userLoginDAO.userlist(teamTarget.getteamid());	
        mav.addObject("team", team);
        mav.addObject("userlist", members);
        mav.addObject("teamTarget", teamTarget);
        mav.addObject("userTarget", newUserTarget);
        return mav;
    }

    @RequestMapping(value = "/editUserTarget", method = RequestMethod.GET)
    public ModelAndView editUserTarget(HttpServletRequest request) {
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        UserTarget editUserTarget = userTargetDAO.get(targetid);
        TeamTarget teamTarget = teamTargetDAO.get(editUserTarget.getteamtargetid());
        Team team = teamDAO.get(teamTarget.getteamid());
        ModelAndView mav = new ModelAndView("userTargetForm");
        List<String> members = userLoginDAO.userlist(teamTarget.getteamid());	
        mav.addObject("team", team);
        mav.addObject("userlist", members);
        mav.addObject("teamTarget", teamTarget);
        mav.addObject("userTarget", editUserTarget);
        return mav;
    } 
    
}
