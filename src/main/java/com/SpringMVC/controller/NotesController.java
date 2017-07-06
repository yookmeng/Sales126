package com.SpringMVC.controller;

import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
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
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.NotesDAO;
import com.SpringMVC.dao.ProspectDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.APINotes;
import com.SpringMVC.model.Activity;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Notes;
import com.SpringMVC.model.Prospect;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.NotesRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class NotesController {

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ProspectDAO prospectDAO;

    @Autowired
    private ActivityDAO activityDAO;

    @Autowired
    private NotesDAO notesDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }
    
    @RequestMapping(value = NotesRestURIConstant.Get, method = RequestMethod.GET)
	public String getNotes(@PathVariable int noteid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(notesDAO.get(noteid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = NotesRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getAllNotes(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
			case USER:
				jsonInString = mapper.writeValueAsString(notesDAO.list(userLogin.getuserid()));
				break;    	   
			case TL:
				Team team = teamDAO.getByUser(userLogin.getuserid());
				jsonInString = mapper.writeValueAsString(notesDAO.listByTeam(team.getteamid()));
				break;    	   
			case MA:
				Branch branch = branchDAO.getByMA(userLogin.getuserid());
				jsonInString = mapper.writeValueAsString(notesDAO.listByBranch(branch.getbranchid()));
				break;    	   
			case MD:
				jsonInString = mapper.writeValueAsString(notesDAO.listByCompany(userLogin.getcompanyid()));
				break;
			default:
				break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = NotesRestURIConstant.Add, method = RequestMethod.POST)
    public ResponseEntity<Notes> addNotes(@RequestBody APINotes aPINotes) throws IOException, ParseException  {
    	UserLogin userLogin = userLoginDAO.findUserEmail(aPINotes.getuseremail());
    	Notes notes = new Notes();
    	notes.setnoteid(aPINotes.getnoteid());
    	notes.setnotedate(aPINotes.getnotedate());
    	notes.setnotetime(aPINotes.getnotetime());    		
    	notes.setuserid(userLogin.getuserid());
    	notes.setusername(userLogin.getusername());
    	notes.setteamid(userLogin.getteamid());
    	notes.setteamname(userLogin.getteamname());
    	notes.setbranchid(userLogin.getbranchid());
    	notes.setbranchname(userLogin.getbranchname());
    	notes.setcompanyid(userLogin.getcompanyid());
    	notes.setcompanyname(userLogin.getcompanyname());
    	notes.setprospectid(aPINotes.getprospectid());
    	notes.setprospectname(aPINotes.getprospectname());
    	notes.setnote(aPINotes.getnote());
    	notes.setstatus(aPINotes.getstatus());
    	notes.setremark(aPINotes.getremark());
    	notes.setread(false);
    	notesDAO.save(notes);
        return new ResponseEntity<Notes>(notes, HttpStatus.CREATED);
    }

    @RequestMapping(value = NotesRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Notes> createNotes(@RequestBody Notes notes) throws IOException {
    	notesDAO.save(notes);
        return new ResponseEntity<Notes>(notes, HttpStatus.CREATED);
    }

    @RequestMapping(value = NotesRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Notes> updateNotes(@RequestBody Notes notes) {
    	Notes currentNotes = notesDAO.get(notes.getnoteid());
         
        if (currentNotes==null) {
            return new ResponseEntity<Notes>(HttpStatus.NOT_FOUND);
        }
        currentNotes.setnotedate(notes.getnotedate());
        currentNotes.setnotetime(notes.getnotetime());
        currentNotes.setnote(notes.getnote());
        currentNotes.setstatus(notes.getstatus());
        currentNotes.setremark(notes.getremark());
        currentNotes.setread(notes.getread());
        currentNotes.setreviewby(notes.getreviewby());
        currentNotes.setreviewdate(notes.getreviewdate());
        currentNotes.setreviewtime(notes.getreviewtime());

        notesDAO.update(currentNotes);
        return new ResponseEntity<Notes>(notes, HttpStatus.OK);
    }

    @RequestMapping(value = NotesRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Notes> deleteNotes(@RequestBody Notes notes) {
        if (notes == null) {
            return new ResponseEntity<Notes>(notes, HttpStatus.NOT_FOUND);
        }
        notesDAO.delete(notes.getnoteid());
        return new ResponseEntity<Notes>(notes, HttpStatus.OK);
    }

    @RequestMapping(value="/listNote", method = RequestMethod.GET)
    public ModelAndView listNote(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        Prospect prospect = prospectDAO.get(prospectid);
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Team team = teamDAO.get(userLogin.getteamid());
 	    List<Activity> listActivity= activityDAO.list(prospectid);
 	    List<Notes> listNotes= notesDAO.listByProspect(prospectid);
        ModelAndView mav = new ModelAndView("noteList");
		mav.addObject("role", userLogin.getrole());
		mav.addObject("team", team);
		mav.addObject("prospect", prospect);
        mav.addObject("listActivity", listActivity);
        mav.addObject("listNotes", listNotes);
 	    return mav;
 	}
    
    @RequestMapping(value="/listNotes", method = RequestMethod.GET)
    public ModelAndView listNotes(HttpServletRequest request) {
    	UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        ModelAndView mav = new ModelAndView("notesList");
        mav.addObject("role", userLogin.getrole());
        Roles role = Roles.valueOf(userLogin.getrole()); 
        switch (role){
        case MD:
            mav.addObject("listNotes", notesDAO.listByCompany(userLogin.getcompanyid()));
            break;
        case MA:
            Branch branch = branchDAO.getByMA(userLogin.getuserid());
            mav.addObject("branch", branch);
            mav.addObject("listNotes", notesDAO.listByBranch(branch.getbranchid()));
            break;
        case TL:
            Team team = teamDAO.getByUser(userLogin.getuserid());
            mav.addObject("team", team);
            mav.addObject("listNotes", notesDAO.listByTeam(team.getteamid()));
            break;
        default:
            mav.addObject("listNotes", notesDAO.list(userLogin.getuserid()));
        }
 	    return mav;
 	}

    @RequestMapping(value = "/addNotes", method = RequestMethod.GET)
    public ModelAndView addReview(HttpServletRequest request) {
        int prospectid = Integer.parseInt(request.getParameter("prospectid"));
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Team team = teamDAO.get(userLogin.getteamid());
        Branch branch = branchDAO.get(userLogin.getbranchid());
        Company company = companyDAO.get(userLogin.getcompanyid());
        Notes newNotes = new Notes();
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        newNotes.setnotedate(java.sql.Date.valueOf(date.toString()));
        newNotes.setuserid(userLogin.getuserid());
        newNotes.setusername(userLogin.getusername());
        newNotes.setteamid(team.getteamid());
        newNotes.setteamname(team.getteamname());
        newNotes.setbranchid(branch.getbranchid());
        newNotes.setbranchname(branch.getbranchname());
        newNotes.setcompanyid(company.getcompanyid());
        newNotes.setcompanyname(company.getcompanyname());
        newNotes.setprospectid(prospectid);
        newNotes.setstatus(0);
        newNotes.setreviewby(userLogin.getuserid());  
        ModelAndView mav = new ModelAndView("notesForm");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("notes", newNotes);
        return mav;
    }

    @RequestMapping(value = "/editNotes", method = RequestMethod.GET)
    public ModelAndView editNotes(HttpServletRequest request) {
        int noteid = Integer.parseInt(request.getParameter("noteid")); 
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Branch branch = branchDAO.get(userLogin.getbranchid());
        Team team = teamDAO.get(userLogin.getteamid());
        Notes editNotes = notesDAO.get(noteid);
        ModelAndView mav = new ModelAndView("notesForm");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("branch", branch);
        mav.addObject("team", team);
        mav.addObject("notes", editNotes);
        return mav;
    }    
}
