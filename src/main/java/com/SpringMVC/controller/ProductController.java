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

import com.SpringMVC.dao.ProductDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Product;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ProductRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ProductController {

    @Autowired
    private ProductDAO productDAO;

	@Autowired
    private UserLoginDAO userLoginDAO;

	@RequestMapping(value = ProductRestURIConstant.Get, method = RequestMethod.GET)
	public String getProduct(@PathVariable int productid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(productDAO.get(productid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ProductRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getProducts(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(productDAO.getAll(userLogin.getcompanyid()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ProductRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws IOException {
    	productDAO.save(product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @RequestMapping(value = ProductRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Product> updateModel(@RequestBody Product product) {
    	Product currentProduct = productDAO.get(product.getproductid());
         
        if (currentProduct ==null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        currentProduct.setproductname(product.getproductname());
        currentProduct.setprice(product.getprice());

        productDAO.update(currentProduct);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping(value = ProductRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@RequestBody Product product) {
        if (product == null) {
            return new ResponseEntity<Product>(product, HttpStatus.NOT_FOUND);
        }
        productDAO.delete(product.getproductid());
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }    
}
