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

import com.SpringMVC.dao.CountryDAO;
import com.SpringMVC.model.Country;
import com.SpringMVC.uriconstant.CountryRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class CountryController {

	@Autowired
    private CountryDAO countryDAO;

    @RequestMapping(value = CountryRestURIConstant.Get, method = RequestMethod.GET)
	public String getCountry(@PathVariable int countryid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(countryDAO.get(countryid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CountryRestURIConstant.GetAll, method = RequestMethod.GET)
	public String getCountries() {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(countryDAO.list());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = CountryRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Country> createCountry(@RequestBody Country country) throws IOException {
    	countryDAO.save(country);
        return new ResponseEntity<Country>(country, HttpStatus.CREATED);
    }

    @RequestMapping(value = CountryRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Country> updateCountry(@RequestBody Country country) {
    	Country currentCountry = countryDAO.get(country.getcountryid());
         
        if (currentCountry==null) {
            return new ResponseEntity<Country>(country, HttpStatus.NOT_FOUND);
        }
        
        currentCountry.setcountrycode(country.getcountrycode());
        currentCountry.setcountryname(country.getcountryname());
        currentCountry.setiddcode(country.getiddcode());

        countryDAO.update(currentCountry);
        return new ResponseEntity<Country>(country, HttpStatus.OK);
    }

    @RequestMapping(value = CountryRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Country> deleteCountry(@RequestBody Country country) {
        if (country == null) {
            return new ResponseEntity<Country>(country, HttpStatus.NOT_FOUND);
        }
 
        countryDAO.delete(country.getcountryid());
        return new ResponseEntity<Country>(country, HttpStatus.OK);
    }
}
