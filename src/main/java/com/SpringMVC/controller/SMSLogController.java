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

import com.SpringMVC.dao.SMSLogDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.SMSLog;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.SMSLogRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class SMSLogController {

	@Autowired
    private SMSLogDAO smsLogDAO;

    @Autowired
    private UserLoginDAO userLoginDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = SMSLogRestURIConstant.Get, method = RequestMethod.GET)
	public String getSMSLog(@PathVariable int smsid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(smsLogDAO.get(smsid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = SMSLogRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getSMSLogs(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
			case USER:
				jsonInString = mapper.writeValueAsString(smsLogDAO.list(userLogin.getuserid()));
				break;    	   
			case TL:
				jsonInString = mapper.writeValueAsString(smsLogDAO.listByTeam(userLogin.getteamid()));
				break;    	   
			case MA:
				jsonInString = mapper.writeValueAsString(smsLogDAO.listByBranch(userLogin.getbranchid()));
				break;    	   
			case MD:
				jsonInString = mapper.writeValueAsString(smsLogDAO.listByCompany(userLogin.getcompanyid()));
				break;
			default:
				break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = SMSLogRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<SMSLog> createSMSLog(@RequestBody SMSLog smsLog) throws IOException {
    	smsLogDAO.save(smsLog);
    	smsLogDAO.send(smsLog.getmobile(), smsLog.getmessage());
        return new ResponseEntity<SMSLog>(smsLog, HttpStatus.CREATED);
    }

    @RequestMapping(value = SMSLogRestURIConstant.Send, method = RequestMethod.POST)
    public void sendSMS(@RequestBody SMSLog smsLog) throws IOException {
    	smsLogDAO.send(smsLog.getmobile(), smsLog.getmessage());
    }
}
