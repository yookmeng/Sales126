package com.SpringMVC.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.SpringMVC.dao.BrandDAO;
import com.SpringMVC.dao.CodeMasterDAO;
import com.SpringMVC.dao.ModelDAO;
import com.SpringMVC.dao.QuestionaireDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.Brand;
import com.SpringMVC.model.Model;
import com.SpringMVC.model.Questionaire;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.uriconstant.QuestionaireRestURIConstant;

@EnableWebMvc
@RestController
public class QuestionaireController {

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private CodeMasterDAO codeMasterDAO;

    @Autowired
    private BrandDAO brandDAO;

    @Autowired
    private ModelDAO modelDAO;

    @Autowired
    private QuestionaireDAO questionaireDAO;

    @RequestMapping(value = "/addQuestionaire", method = RequestMethod.GET)
    public ModelAndView addQuestionaire(HttpServletRequest request) {
    	UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
    	int companyid = userLogin.getcompanyid();
    	Questionaire newQuestionaire = new Questionaire();
    	newQuestionaire.setuserid(userLogin.getuserid());
        ModelAndView mav = new ModelAndView("questionaireForm");
        List<String> sources = codeMasterDAO.getType("SOURCE");	
        mav.addObject("sourcelist", sources);
        List<String> brands = brandDAO.getSellingBrands(companyid);
        mav.addObject("brandlist", brands);
        Brand brand = brandDAO.getByName(brands.get(0));
        List<String> models = modelDAO.getSellingModels(brand.getbrandid());	
        mav.addObject("modellist", models);
        mav.addObject("questionaire", newQuestionaire);
        return mav;
    }

    @RequestMapping(value = QuestionaireRestURIConstant.CREATE, method = RequestMethod.POST)
    public ResponseEntity<Questionaire> createQuestionaire(@RequestBody Questionaire questionaire) throws IOException {
    	Brand brand = brandDAO.getByName(questionaire.getbrandname());
    	questionaire.setbrandid(brand.getbrandid());
    	Model model = modelDAO.getByName(questionaire.getmodelname());
    	questionaire.setmodelid(model.getmodelid());
    	questionaire.setdemo(false);
    	questionaire.settestdrive(false);
    	questionaire.setquotation(false);
    	questionaireDAO.save(questionaire);
        return new ResponseEntity<Questionaire>(questionaire, HttpStatus.CREATED);
    }    
}
