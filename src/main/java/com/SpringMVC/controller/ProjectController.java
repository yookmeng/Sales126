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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.SpringMVC.dao.ProjectDAO;
import com.SpringMVC.dao.SMSLogDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Project;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ProjectRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ProjectController {

	@Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Autowired
    private SMSLogDAO smsLogDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = ProjectRestURIConstant.Get, method = RequestMethod.GET)
	public String getProject(@PathVariable int projectid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(projectDAO.get(projectid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ProjectRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getAllProject(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
				case USER:
					jsonInString = mapper.writeValueAsString(projectDAO.list(userLogin.getuserid()));
					break;    	   
				case TL:
					jsonInString = mapper.writeValueAsString(projectDAO.listByTeam(userLogin.getteamid()));
					break;    	   
				case MA:
					jsonInString = mapper.writeValueAsString(projectDAO.listByBranch(userLogin.getbranchid()));
					break;    	   
				case MD:
					jsonInString = mapper.writeValueAsString(projectDAO.listByCompany(userLogin.getcompanyid()));
					break;
				default:
					break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ProjectRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Project> createProject(@RequestBody Project project) throws IOException {
    	UserLogin userLogin = userLoginDAO.findUser(project.getuserid());
        projectDAO.save(project);
    	if (project.getsmsflag()){
    		String defaultMessage = 
    				"Hi! " + project.getname() + 
    				", I'm " + userLogin.getusername() + 
    				" from MyTaman Marketing Team. " +
    				"If you have further inquiry, please do not hesitate to call me. " +
    				"My contact no is " + userLogin.getmobile() + ".";
    		smsLogDAO.send(project.getmobile(), defaultMessage);
    	};
        
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @RequestMapping(value = ProjectRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Project> updateProject(@RequestBody Project project, HttpServletRequest request) {
    	Project currentProject = projectDAO.get(project.getprojectid());
         
        if (currentProject==null) {
            return new ResponseEntity<Project>(project, HttpStatus.NOT_FOUND);
        }
        
        currentProject.setprojectname(project.getprojectname());
        currentProject.setaddress(project.getaddress());
        currentProject.setname(project.getname());
        currentProject.setmobile(project.getmobile());
        currentProject.setemail(project.getemail());
        currentProject.settitleid(project.gettitleid());
        currentProject.setpropertyid(project.getpropertyid());
        currentProject.setunits(project.getunits());
        currentProject.setstatus(project.getstatus());
        currentProject.setsmsflag(project.getsmsflag());
        currentProject.setforecastperiod(project.getforecastperiod());
        currentProject.sethwdiscount(project.gethwdiscount());
        currentProject.setswdiscount(project.getswdiscount());

        projectDAO.update(currentProject);
        projectDAO.createpdf(currentProject, request);        
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @RequestMapping(value = ProjectRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Project> deleteProject(@RequestBody Project project) {
        if (project == null) {
            return new ResponseEntity<Project>(project, HttpStatus.NOT_FOUND);
        }
 
        projectDAO.delete(project.getprojectid());
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
}
