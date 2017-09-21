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

import com.SpringMVC.dao.OrderDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Order;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.OrderRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class OrderController {

	@Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private OrderDAO orderDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }

    @RequestMapping(value = OrderRestURIConstant.Get, method = RequestMethod.GET)
	public String getOrder(@PathVariable int orderid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(orderDAO.get(orderid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = OrderRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getAllOrder(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
				case USER:
					jsonInString = mapper.writeValueAsString(orderDAO.list(userLogin.getuserid()));
					break;    	   
				case TL:
					jsonInString = mapper.writeValueAsString(orderDAO.listByTeam(userLogin.getteamid()));
					break;    	   
				case MA:
					jsonInString = mapper.writeValueAsString(orderDAO.listByBranch(userLogin.getbranchid()));
					break;    	   
				case MD:
					jsonInString = mapper.writeValueAsString(orderDAO.listByCompany(userLogin.getcompanyid()));
					break;
				default:
					break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = OrderRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order) throws IOException {
        orderDAO.save(order);
        return new ResponseEntity<Order>(order, HttpStatus.CREATED);
    }

    @RequestMapping(value = OrderRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
    	Order currentOrder = orderDAO.get(order.getorderid());
         
        if (currentOrder==null) {
            return new ResponseEntity<Order>(order, HttpStatus.NOT_FOUND);
        }
        
        currentOrder.setquantity(order.getquantity());
        currentOrder.setprice(order.getprice());
        currentOrder.setamount(order.getamount());

        orderDAO.update(currentOrder);
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @RequestMapping(value = OrderRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteOrder(@RequestBody Order order) {
        if (order == null) {
            return new ResponseEntity<Order>(order, HttpStatus.NOT_FOUND);
        }
 
        orderDAO.delete(order.getorderid());
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }
}
