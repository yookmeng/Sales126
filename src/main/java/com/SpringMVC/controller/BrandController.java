package com.SpringMVC.controller;

import java.util.List;
import java.io.IOException;
import java.security.Principal;
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

import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.BrandRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class BrandController {

	@Autowired
    private UserLoginDAO userLoginDAO;

	@Autowired
    private BrandDAO brandDAO;

    @RequestMapping(value = BrandRestURIConstant.Get, method = RequestMethod.GET)
	public String getBrand(@PathVariable int brandid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(brandDAO.get(brandid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = BrandRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getBrands(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(brandDAO.list(userLogin.getcompanyid()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = BrandRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) throws IOException {
    	brandDAO.save(brand);
        return new ResponseEntity<Brand>(brand, HttpStatus.CREATED);
    }

    @RequestMapping(value = BrandRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand) {
    	Brand currentBrand = brandDAO.get(brand.getbrandid());
         
        if (currentBrand==null) {
            return new ResponseEntity<Brand>(brand, HttpStatus.NOT_FOUND);
        }
        
        currentBrand.setbrandname(brand.getbrandname());
        currentBrand.setsellingbrand(brand.getsellingbrand());

        brandDAO.update(currentBrand);
        return new ResponseEntity<Brand>(brand, HttpStatus.OK);
    }

    @RequestMapping(value = BrandRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Brand> deleteBrand(@RequestBody Brand brand) {
        if (brand == null) {
            return new ResponseEntity<Brand>(brand, HttpStatus.NOT_FOUND);
        }
 
        brandDAO.delete(brand.getbrandid());
        return new ResponseEntity<Brand>(brand, HttpStatus.OK);
    }
    
    @RequestMapping(value="/listBrand", method = RequestMethod.GET)
    public ModelAndView listBrand(Principal principal) {
    	UserLogin userLogin = userLoginDAO.get(principal.getName());
 	    List<Brand> listBrand = brandDAO.list(userLogin.getcompanyid());
        ModelAndView mav = new ModelAndView("brandList");
        mav.addObject("listBrand", listBrand);
 	    return mav;
 	}

    @RequestMapping(value = "/addBrand", method = RequestMethod.GET)
    public ModelAndView addBrand(HttpServletRequest request) {
    	UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Brand newBrand = new Brand();
        newBrand.setcompanyid(userLogin.getcompanyid());
        ModelAndView mav = new ModelAndView("brandForm");
        mav.addObject("brand", newBrand);
        return mav;
    }

    @RequestMapping(value = "/editBrand", method = RequestMethod.GET)
    public ModelAndView editBrand(HttpServletRequest request) {
        int brandid = Integer.parseInt(request.getParameter("brandid"));        
        Brand editBrand = brandDAO.get(brandid);
        ModelAndView mav = new ModelAndView("brandForm");
        mav.addObject("brand", editBrand);
        return mav;
    }   
}
