package com.SpringMVC.controller;

import java.io.IOException;
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

import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.ModelRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ModelController {

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ModelDAO modelDAO;

	@Autowired
    private UserLoginDAO userLoginDAO;

	@RequestMapping(value = ModelRestURIConstant.Get, method = RequestMethod.GET)
	public String getModel(@PathVariable int modelid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(modelDAO.get(modelid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ModelRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getModel(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(modelDAO.list(userLogin.getcompanyid()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ModelRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Model> createModel(@RequestBody Model model) throws IOException {
    	modelDAO.save(model);
        return new ResponseEntity<Model>(model, HttpStatus.CREATED);
    }

    @RequestMapping(value = ModelRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Model> updateModel(@RequestBody Model model) {
    	Model currentModel = modelDAO.get(model.getmodelid());
         
        if (currentModel==null) {
            return new ResponseEntity<Model>(HttpStatus.NOT_FOUND);
        }
        currentModel.setmodelname(model.getmodelname());
        currentModel.setsellingmodel(model.getsellingmodel());        
        currentModel.setcommission(model.getcommission());
        currentModel.setprice(model.getprice());
        currentModel.setsuminsured(model.getsuminsured());
        currentModel.setpremium(model.getpremium());
        currentModel.setroadtax(model.getroadtax());
        currentModel.setcolour(model.getcolour());
        currentModel.setenginetype(model.getenginetype());
        currentModel.setfuelsupplysystem(model.getfuelsupplysystem());
        currentModel.setdisplacement(model.getdisplacement());
        currentModel.setmaxpower(model.getmaxpower());
        currentModel.setmaxtorque(model.getmaxtorque());
        currentModel.settransmission(model.gettransmission());

        modelDAO.update(currentModel);
        return new ResponseEntity<Model>(model, HttpStatus.OK);
    }

    @RequestMapping(value = ModelRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Model> deleteModel(@RequestBody Model model) {
        if (model == null) {
            return new ResponseEntity<Model>(model, HttpStatus.NOT_FOUND);
        }
        modelDAO.delete(model.getmodelid());
        return new ResponseEntity<Model>(model, HttpStatus.OK);
    }
    
    @RequestMapping(value="/listModel", method = RequestMethod.GET)
    public ModelAndView listModel(HttpServletRequest request) {
        int brandid = Integer.parseInt(request.getParameter("brandid"));
        Brand brand = brandDAO.get(brandid);
 	    List<Model> listModel = modelDAO.list(brandid);
        ModelAndView mav = new ModelAndView("modelList");
        mav.addObject("brand", brand);
 	    mav.addObject("listModel", listModel);
 	    return mav;
 	}

    @RequestMapping(value = "/addModel", method = RequestMethod.GET)
    public ModelAndView addModel(HttpServletRequest request) {
        int brandid = Integer.parseInt(request.getParameter("brandid"));
        Brand brand = brandDAO.get(brandid);
        Model newModel = new Model();
        newModel.setbrandid(brandid);
        ModelAndView mav = new ModelAndView("modelForm");
        mav.addObject("brand", brand);
        mav.addObject("model", newModel);
        return mav;
    }

    @RequestMapping(value = "/editModel", method = RequestMethod.GET)
    public ModelAndView editModel(HttpServletRequest request) {
        int modelid = Integer.parseInt(request.getParameter("modelid"));   
        Model editModel = modelDAO.get(modelid);
        Brand brand = brandDAO.get(editModel.getbrandid());
        ModelAndView mav = new ModelAndView("modelForm");
        mav.addObject("brand", brand);
        mav.addObject("model", editModel);
        return mav;
    }   
}
