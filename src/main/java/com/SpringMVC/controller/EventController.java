package com.SpringMVC.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.SpringMVC.dao.EventDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Event;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.EventRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class EventController {

	@Autowired
    private EventDAO eventDAO;

    @Autowired
    private UserLoginDAO userLoginDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = EventRestURIConstant.Get, method = RequestMethod.GET)
	public String getEvent(@PathVariable int id) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(eventDAO.get(id));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = EventRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getEvents(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
			case USER:
				jsonInString = mapper.writeValueAsString(eventDAO.list(userLogin.getuserid()));
				break;    	   
			case TL:
				jsonInString = mapper.writeValueAsString(eventDAO.listByTeam(userLogin.getteamid()));
				break;    	   
			case MA:
				jsonInString = mapper.writeValueAsString(eventDAO.listByBranch(userLogin.getbranchid()));
				break;    	   
			case MD:
				jsonInString = mapper.writeValueAsString(eventDAO.listByCompany(userLogin.getcompanyid()));
				break;
			default:
				break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = EventRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Event> createEvent(@RequestBody Event event) throws IOException {
    	eventDAO.save(event);
        return new ResponseEntity<Event>(event, HttpStatus.CREATED);
    }

    @RequestMapping(value = EventRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
    	Event currentEvent = eventDAO.get(event.getid());
         
        if (currentEvent==null) {
            return new ResponseEntity<Event>(event, HttpStatus.NOT_FOUND);
        }
        
        currentEvent.settitle(event.gettitle());
        currentEvent.setremark(event.getremark());
        currentEvent.setperiod(event.getperiod());
        currentEvent.setstartdate(event.getstartdate());
        currentEvent.setstarttime(event.getstarttime());
        currentEvent.setenddate(event.getenddate());
        currentEvent.setendtime(event.getendtime());
        currentEvent.seturl(event.geturl());
        currentEvent.setallDay(event.getallDay());
        currentEvent.setstatus(event.getstatus());

        eventDAO.update(currentEvent);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @RequestMapping(value = EventRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteEvent(@RequestBody Event event) {
        if (event == null) {
            return new ResponseEntity<Event>(event, HttpStatus.NOT_FOUND);
        }
 
        eventDAO.delete(event.getid());
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }
}
