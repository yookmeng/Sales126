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

import com.SpringMVC.dao.CityDAO;
import com.SpringMVC.model.City;
import com.SpringMVC.uriconstant.CityRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class CityController {

	@Autowired
    private CityDAO cityDAO;

    @RequestMapping(value = CityRestURIConstant.Get, method = RequestMethod.GET)
	public String getCity(@PathVariable int cityid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(cityDAO.get(cityid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CityRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getCities() {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(cityDAO.list());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CityRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<City> createCity(@RequestBody City city) throws IOException {
    	cityDAO.save(city);
        return new ResponseEntity<City>(city, HttpStatus.CREATED);
    }

    @RequestMapping(value = CityRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<City> updateCity(@RequestBody City city) {
    	City currentCity = cityDAO.get(city.getcityid());
         
        if (currentCity==null) {
            return new ResponseEntity<City>(city, HttpStatus.NOT_FOUND);
        }
        
        currentCity.setcityname(city.getcityname());

        cityDAO.update(currentCity);
        return new ResponseEntity<City>(city, HttpStatus.OK);
    }

    @RequestMapping(value = CityRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<City> deleteCity(@RequestBody City city) {
        if (city == null) {
            return new ResponseEntity<City>(city, HttpStatus.NOT_FOUND);
        }
 
        cityDAO.delete(city.getcityid());
        return new ResponseEntity<City>(city, HttpStatus.OK);
    }
}
