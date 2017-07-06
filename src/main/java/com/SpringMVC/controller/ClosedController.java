package com.SpringMVC.controller;

import java.io.IOException;

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
import com.SpringMVC.dao.ClosedDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Activity;
import com.SpringMVC.model.Closed;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ClosedRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ClosedController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ProspectDAO prospectDAO;

    @Autowired
    private ActivityDAO activityDAO;

    @Autowired
    private ClosedDAO closedDAO;

    @RequestMapping(value = ClosedRestURIConstant.Get, method = RequestMethod.GET)
	public String getClosed(@PathVariable int closedid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(closedDAO.get(closedid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ClosedRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Closed> createClosed(@RequestBody Closed closed, 
    		HttpServletRequest request) throws IOException {
    	closedDAO.save(closed);
        return new ResponseEntity<Closed>(closed, HttpStatus.CREATED);
    }

    @RequestMapping(value = ClosedRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Closed> updateClosed(@PathVariable("closedid") int closedid, 
    		@RequestBody Closed closed, HttpServletRequest request) {
    	Closed currentClosed = closedDAO.get(closedid);
         
        if (currentClosed==null) {
            return new ResponseEntity<Closed>(HttpStatus.NOT_FOUND);
        }
        
    	currentClosed.setclosedate(closed.getclosedate());
    	currentClosed.setdownpayment(closed.getdownpayment());
    	currentClosed.setremark(closed.getremark());

        closedDAO.update(currentClosed);
        return new ResponseEntity<Closed>(closed, HttpStatus.OK);
    }

    @RequestMapping(value = ClosedRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Closed> deleteClosed(@PathVariable("closedid") int closedid) {
    	Closed closed = closedDAO.get(closedid);
        if (closed == null) {
            return new ResponseEntity<Closed>(HttpStatus.NOT_FOUND);
        }
 
        closedDAO.delete(closedid);
        return new ResponseEntity<Closed>(HttpStatus.OK);
    }

    @RequestMapping(value = "/addClosed", method = RequestMethod.GET)
    public ModelAndView addClosed(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        int activityid = activityDAO.getlastactivityid(prospectid);
        Activity activity = activityDAO.get(activityid);
        Prospect prospect = prospectDAO.get(prospectid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Closed newClosed = new Closed();
        newClosed.setclosedate(activity.getactivitydate());
        newClosed.setprospectid(activity.getprospectid());
        newClosed.setactivityid(activity.getactivityid());
        ModelAndView mav = new ModelAndView("closedForm");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("prospect", prospect);
        mav.addObject("closed", newClosed);
        return mav;
    }
            
    @RequestMapping(value = "/editClosed", method = RequestMethod.GET)
    public ModelAndView editClosed(HttpServletRequest request) {
        int closedid = Integer.parseInt(request.getParameter("closedid"));       	
        Closed editClosed = closedDAO.get(closedid);
        Prospect prospect = prospectDAO.get(editClosed.getprospectid());
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("closedForm");
 	   	mav.addObject("role", userLogin.getrole());
        mav.addObject("prospect", prospect);
        mav.addObject("closed", editClosed);
        return mav;
    }
}
