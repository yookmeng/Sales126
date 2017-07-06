package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
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

import com.SpringMVC.dao.ContactDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Contact;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ContactRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ContactController {
    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private ContactDAO contactDAO;

    @RequestMapping(value = ContactRestURIConstant.Get, method = RequestMethod.GET)
	public String getContact(@PathVariable int contactid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(contactDAO.get(contactid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ContactRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getAllContact(Principal principal) {
    	UserLogin userLogin = userLoginDAO.get(principal.getName());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(contactDAO.list(userLogin.getuserid()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ContactRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) throws IOException {
        if (contactDAO.isExist(contact)) {
            return new ResponseEntity<Contact>(HttpStatus.CONFLICT);
        }
        contactDAO.save(contact);
        return new ResponseEntity<Contact>(contact, HttpStatus.CREATED);
    }

    @RequestMapping(value = ContactRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Contact> updateContact(@PathVariable("contactid") int contactid, @RequestBody Contact contact) {
    	Contact currentContact = contactDAO.get(contactid);
         
        if (currentContact==null) {
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }
        
        currentContact.setfirstname(contact.getfirstname());
        currentContact.setlastname(contact.getlastname());
        currentContact.setmobile(contact.getmobile());
        currentContact.sethome(contact.gethome());
        currentContact.setwork(contact.getwork());
        currentContact.setemail(contact.getemail());
        currentContact.setbirthday(contact.getbirthday());
        currentContact.setaddress(contact.getaddress());
        currentContact.setcompany(contact.getcompany());
        currentContact.settitle(contact.gettitle());
        currentContact.setnote(contact.getnote());
        currentContact.setwebsite(contact.getwebsite());

        contactDAO.update(currentContact);
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    @RequestMapping(value = ContactRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Contact> deleteContact(@PathVariable("contactid") int contactid) {
        Contact contact = contactDAO.get(contactid);
        if (contact == null) {
            return new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
        }
 
        contactDAO.delete(contactid);
        return new ResponseEntity<Contact>(HttpStatus.OK);
    }

    @RequestMapping(value="/listContacts", method = RequestMethod.GET)
    public ModelAndView listContacts(Principal principal) {
        UserLogin userLogin = userLoginDAO.get(principal.getName());
 	    ModelAndView mav = new ModelAndView("contactList");
		List<Contact> listContact = contactDAO.list(userLogin.getuserid());
		mav.addObject("userLogin", userLogin);
		mav.addObject("listContact", listContact);        	
 	    return mav;
 	}
    
    @RequestMapping(value = "/addContact", method = RequestMethod.GET)
    public ModelAndView addContact(HttpServletRequest request) {
    	UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
    	Contact newContact = new Contact();
        newContact.setcontactid(0);
        newContact.setuserid(userLogin.getuserid());
        ModelAndView mav = new ModelAndView("contactForm");
        mav.addObject("contact", newContact);
        return mav;
    }
    @RequestMapping(value = "/editContact", method = RequestMethod.GET)
    public ModelAndView editContact(HttpServletRequest request) {
        int contactid = Integer.parseInt(request.getParameter("contactid")); 
        Contact editContact= contactDAO.get(contactid);
        ModelAndView mav = new ModelAndView("contactForm");
        mav.addObject("contact", editContact);
        return mav;
    } 
}
