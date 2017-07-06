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

import com.SpringMVC.dao.StateDAO;
import com.SpringMVC.model.State;
import com.SpringMVC.uriconstant.StateRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class StateController {

	@Autowired
    private StateDAO stateDAO;

    @RequestMapping(value = StateRestURIConstant.Get, method = RequestMethod.GET)
	public String getState(@PathVariable int stateid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(stateDAO.get(stateid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = StateRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getStates() {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(stateDAO.list());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = StateRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<State> createState(@RequestBody State state) throws IOException {
    	stateDAO.save(state);
        return new ResponseEntity<State>(state, HttpStatus.CREATED);
    }

    @RequestMapping(value = StateRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<State> updateState(@RequestBody State state) {
    	State currentState = stateDAO.get(state.getstateid());
         
        if (currentState==null) {
            return new ResponseEntity<State>(state, HttpStatus.NOT_FOUND);
        }
        
        currentState.setstatename(state.getstatename());

        stateDAO.update(currentState);
        return new ResponseEntity<State>(state, HttpStatus.OK);
    }

    @RequestMapping(value = StateRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<State> deleteState(@RequestBody State state) {
        if (state == null) {
            return new ResponseEntity<State>(state, HttpStatus.NOT_FOUND);
        }
 
        stateDAO.delete(state.getstateid());
        return new ResponseEntity<State>(state, HttpStatus.OK);
    }
}
