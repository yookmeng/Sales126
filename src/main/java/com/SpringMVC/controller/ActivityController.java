package com.SpringMVC.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
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
import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.APIActivity;
import com.SpringMVC.model.Activity;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ActivityRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ActivityController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ProspectDAO prospectDAO;

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private ActivityDAO activityDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = ActivityRestURIConstant.Get, method = RequestMethod.GET)
	public String getActivity(@PathVariable int activityid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(activityDAO.get(activityid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ActivityRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getAllActivity(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
			case USER:
				jsonInString = mapper.writeValueAsString(activityDAO.listByUser(userLogin.getuserid()));
				break;    	   
			case TL:
				jsonInString = mapper.writeValueAsString(activityDAO.listByTeam(userLogin.getteamid()));
				break;    	   
			case MA:
				jsonInString = mapper.writeValueAsString(activityDAO.listByBranch(userLogin.getbranchid()));
				break;    	   
			case MD:
				jsonInString = mapper.writeValueAsString(activityDAO.listByCompany(userLogin.getcompanyid()));
				break;
			default:
				break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ActivityRestURIConstant.Add, method = RequestMethod.POST)
    public ResponseEntity<Activity> addActivity(@RequestBody APIActivity aPIActivity) throws IOException {
    	UserLogin userLogin = userLoginDAO.findUserEmail(aPIActivity.getuseremail());
    	Prospect prospect = prospectDAO.get(aPIActivity.getprospectid());
    	Activity activity = new Activity();
    	activity.setactivityid(aPIActivity.getactivityid());
    	activity.setuserid(userLogin.getuserid());
    	activity.setusername(userLogin.getusername());
    	activity.setprospectid(aPIActivity.getprospectid());
    	activity.setfirstname(prospect.getfirstname());
    	activity.setlastname(prospect.getlastname());
    	activity.setactivitydate(aPIActivity.getactivitydate());
    	activity.setactivitytime(aPIActivity.getactivitytime());
    	activity.setbrandname(aPIActivity.getbrandname());
    	Brand brand = brandDAO.getByName(aPIActivity.getbrandname());
    	activity.setbrandid(brand.getbrandid());
    	activity.setmodelname(aPIActivity.getmodelname());
    	Model model = modelDAO.getByName(aPIActivity.getmodelname());
    	activity.setmodelid(model.getmodelid());
    	activity.setdemo(aPIActivity.getdemo());
    	activity.setdemodate(aPIActivity.getdemodate());
    	activity.setdemotime(aPIActivity.getdemotime());
    	activity.settestdrive(aPIActivity.gettestdrive());
    	activity.settestdrivedate(aPIActivity.gettestdrivedate());
    	activity.settestdrivetime(aPIActivity.gettestdrivetime());
    	activity.setquotation(aPIActivity.getquotation());
    	activity.setquotationdate(aPIActivity.getquotationdate());
    	activity.setquotationtime(aPIActivity.getquotationtime());
    	activity.setclosed(aPIActivity.getclosed());
    	activity.setcloseddate(aPIActivity.getcloseddate());
    	activity.setclosedtime(aPIActivity.getclosedtime());

    	activityDAO.save(activity);
        return new ResponseEntity<Activity>(activity, HttpStatus.CREATED);
    }

    @RequestMapping(value = ActivityRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Activity> createActivity(@RequestBody Activity activity) throws IOException {
    	Brand brand = brandDAO.getByName(activity.getbrandname());
    	activity.setbrandid(brand.getbrandid());
    	Model model = modelDAO.getByName(activity.getmodelname());
    	activity.setmodelid(model.getmodelid());

    	activityDAO.save(activity);
        return new ResponseEntity<Activity>(activity, HttpStatus.CREATED);
    }

    @RequestMapping(value = ActivityRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Activity> updateActivity(@RequestBody Activity activity) {
    	Activity currentActivity = activityDAO.get(activity.getactivityid());         
        if (currentActivity==null) {
            return new ResponseEntity<Activity>(HttpStatus.NOT_FOUND);
        }

    	Brand brand = brandDAO.getByName(activity.getbrandname());
    	activity.setbrandid(brand.getbrandid());
    	Model model = modelDAO.getByName(activity.getmodelname());
    	activity.setmodelid(model.getmodelid());
        
        currentActivity.setactivitydate(activity.getactivitydate());
        currentActivity.setactivitytime(activity.getactivitytime());
        currentActivity.setbrandid(activity.getbrandid());
        currentActivity.setbrandname(activity.getbrandname());
        currentActivity.setmodelid(activity.getmodelid());
        currentActivity.setmodelname(activity.getmodelname());
        currentActivity.setdemo(activity.getdemo());
        currentActivity.setdemodate(activity.getdemodate());
        currentActivity.setdemotime(activity.getdemotime());
        currentActivity.settestdrive(activity.gettestdrive());
        currentActivity.settestdrivedate(activity.gettestdrivedate());
        currentActivity.settestdrivetime(activity.gettestdrivetime());
        currentActivity.setquotation(activity.getquotation());
        currentActivity.setquotationdate(activity.getquotationdate());
        currentActivity.setquotationtime(activity.getquotationtime());
        currentActivity.setquotationid(activity.getquotationid());
        currentActivity.setclosed(activity.getclosed());
        currentActivity.setcloseddate(activity.getcloseddate());
        currentActivity.setclosedtime(activity.getclosedtime());

        activityDAO.update(currentActivity);
        return new ResponseEntity<Activity>(activity, HttpStatus.OK);
    }

    @RequestMapping(value = ActivityRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Activity> deleteActivity(@RequestBody Activity activity) {
        if (activity == null) {
            return new ResponseEntity<Activity>(activity, HttpStatus.NOT_FOUND);
        }
 
        activityDAO.delete(activity.getactivityid());
        return new ResponseEntity<Activity>(activity, HttpStatus.OK);
    }
    
    @RequestMapping(value="/listActivity", method = RequestMethod.GET)
    public ModelAndView listActivity(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Prospect prospect = prospectDAO.get(prospectid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Branch branch = branchDAO.get(userLogin.getbranchid());
        Team team = teamDAO.get(userLogin.getteamid());
        List<Activity> listActivity= activityDAO.list(prospectid);
        ModelAndView mav = new ModelAndView("activityList");
		mav.addObject("role", userLogin.getrole());
        mav.addObject("team", team);
        mav.addObject("branch", branch);
        mav.addObject("prospect", prospect);
        mav.addObject("listActivity", listActivity);
 	    return mav;
 	}

    @RequestMapping(value="/listActivities", method = RequestMethod.GET)
    public ModelAndView listActivities(HttpServletRequest request) {
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("activitiesList");
		mav.addObject("role", userLogin.getrole());
		Roles role = Roles.valueOf(userLogin.getrole()); 
		switch (role){
		case USER:
			mav.addObject("listActivity", activityDAO.listByUser(userLogin.getuserid()));
			break;    	   
		case TL:
			Team team = teamDAO.getByUser(userLogin.getuserid());
	        mav.addObject("team", team);
			mav.addObject("listActivity", activityDAO.listByTeam(team.getteamid()));			
			break;    	   
		case MA:
			Branch branch = branchDAO.getByMA(userLogin.getuserid());
	        mav.addObject("branch", branch);
			mav.addObject("listActivity", activityDAO.listByBranch(branch.getbranchid()));			
			break;    	   
		case MD:
			int companyid = userLogin.getcompanyid();
			mav.addObject("listActivity", activityDAO.listByCompany(companyid));			
			break;
		default:
			break;	
		}		
 	    return mav;
 	}

    @RequestMapping(value = "/addActivity", method = RequestMethod.GET)
    public ModelAndView addActivity(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Activity newActivity = new Activity();
        newActivity.setprospectid(prospectid);
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        newActivity.setactivitydate(date);
        Prospect prospect = prospectDAO.get(prospectid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        newActivity.setuserid(userLogin.getuserid());
        ModelAndView mav = new ModelAndView("activityForm");
        List<String> brands = brandDAO.getSellingBrands(userLogin.getcompanyid());	
        mav.addObject("brandlist", brands);
        Brand brand = brandDAO.getByName(brands.get(0));
        List<String> models = modelDAO.getSellingModels(brand.getbrandid());	
		mav.addObject("role", userLogin.getrole());
        mav.addObject("prospect", prospect);
        mav.addObject("modellist", models);
        mav.addObject("activity", newActivity);
        return mav;
    }

    @RequestMapping(value = "/editActivity", method = RequestMethod.GET)
    public ModelAndView editActivity(HttpServletRequest request) {
        int activityid = Integer.parseInt(request.getParameter("activityid")); 
        Activity editActivity = activityDAO.get(activityid);
        Prospect prospect = prospectDAO.get(editActivity.getprospectid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Branch branch = branchDAO.get(userLogin.getbranchid());
        Team team = teamDAO.get(userLogin.getteamid());
        ModelAndView mav = new ModelAndView("activityForm");
        List<String> brands = brandDAO.getSellingBrands(userLogin.getcompanyid());	
        mav.addObject("brandlist", brands);
        Brand brand = brandDAO.getByName(brands.get(0));
        List<String> models = modelDAO.getSellingModels(brand.getbrandid());	
		mav.addObject("role", userLogin.getrole());
        mav.addObject("team", team);
        mav.addObject("branch", branch);
        mav.addObject("prospect", prospect);
        mav.addObject("modellist", models);
        mav.addObject("activity", editActivity);
        return mav;
    } 
}
