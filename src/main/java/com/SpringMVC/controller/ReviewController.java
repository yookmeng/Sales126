package com.SpringMVC.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
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

import com.SpringMVC.dao.BranchDAO;
import com.SpringMVC.dao.ClosingPeriodDAO;
import com.SpringMVC.dao.CompanyDAO;
import com.SpringMVC.dao.ReviewDAO;
import com.SpringMVC.dao.TeamDAO;
import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.dao.UserMonthlySummaryDAO;
import com.SpringMVC.dao.UserTargetDAO;
import com.SpringMVC.model.APIReview;
import com.SpringMVC.model.Branch;
import com.SpringMVC.model.Company;
import com.SpringMVC.model.IonicUser;
import com.SpringMVC.model.Review;
import com.SpringMVC.model.Team;
import com.SpringMVC.model.UserLogin;
import com.SpringMVC.model.UserMonthlySummary;
import com.SpringMVC.model.UserTarget;
import com.SpringMVC.uriconstant.ReviewRestURIConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@RestController
public class ReviewController {

    @Autowired
    private TeamDAO teamDAO;

    @Autowired
    private BranchDAO branchDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private UserLoginDAO userLoginDAO;

    @Autowired
    private UserTargetDAO userTargetDAO;

    @Autowired
    private UserMonthlySummaryDAO userMonthlySummaryDAO;

    @Autowired
    private ClosingPeriodDAO closingPeriodDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    private enum Roles {
        USER, SA, MD, MA, TL, DEV;
    }
    
    @RequestMapping(value = ReviewRestURIConstant.Get, method = RequestMethod.GET)
	public String getReview(@PathVariable int reviewid) {
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			jsonInString = mapper.writeValueAsString(reviewDAO.get(reviewid));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ReviewRestURIConstant.GetAll, method = RequestMethod.POST)
	public String getReviewByMember(@RequestBody IonicUser ionicUser) {
    	UserLogin userLogin = userLoginDAO.findUserEmail(ionicUser.getemail());
    	ObjectMapper mapper = new ObjectMapper();
    	String jsonInString="";
		try {
			Roles role = Roles.valueOf(userLogin.getrole()); 
			switch (role){
			case USER:
				jsonInString = mapper.writeValueAsString(reviewDAO.list(userLogin.getuserid()));
				break;    	   
			case TL:
				Team team = teamDAO.getByUser(userLogin.getuserid());
				jsonInString = mapper.writeValueAsString(reviewDAO.listByTeam(team.getteamid()));
				break;    	   
			case MA:
				Branch branch = branchDAO.getByMA(userLogin.getuserid());
				jsonInString = mapper.writeValueAsString(reviewDAO.listByBranch(branch.getbranchid()));
				break;    	   
			case MD:
				jsonInString = mapper.writeValueAsString(reviewDAO.listByCompany(userLogin.getcompanyid()));
				break;
			default:
				break;	
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonInString;
	}

    @RequestMapping(value = ReviewRestURIConstant.Add, method = RequestMethod.POST)
    public ResponseEntity<Review> addReview(@RequestBody APIReview aPIReview) throws IOException {
    	UserLogin reviewBy = userLoginDAO.findUserEmail(aPIReview.getuseremail());
    	UserLogin userLogin = userLoginDAO.findUserEmail(aPIReview.getemail());
    	Review review = new Review();
    	review.setreviewid(0);
    	review.setperiod(aPIReview.getperiod());
    	review.setuserid(userLogin.getuserid());
    	review.setusername(userLogin.getusername());
    	review.setteamid(userLogin.getteamid());
    	review.setteamname(userLogin.getteamname());
    	review.setbranchid(userLogin.getbranchid());
    	review.setbranchname(userLogin.getbranchname());
    	review.setcompanyid(userLogin.getcompanyid());
    	review.setcompanyname(userLogin.getcompanyname());
    	review.settargetid(aPIReview.gettargetid());
    	review.setteamtargetid(aPIReview.getteamtargetid());
    	review.setreviewdate(aPIReview.getreviewdate());
    	review.setprospect(aPIReview.getprospect());
    	review.settestdrive(aPIReview.getprospect());
    	review.setclosed(aPIReview.getprospect());
    	review.setminute(aPIReview.getminute());
    	review.setreviewby(reviewBy.getuserid());
    	review.setreviewbyname(reviewBy.getusername());
    	reviewDAO.save(review);
        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }

    @RequestMapping(value = ReviewRestURIConstant.Create, method = RequestMethod.POST)
    public ResponseEntity<Review> createReview(@RequestBody Review review) throws IOException {
    	reviewDAO.save(review);
        return new ResponseEntity<Review>(review, HttpStatus.CREATED);
    }

    @RequestMapping(value = ReviewRestURIConstant.Update, method = RequestMethod.POST)
    public ResponseEntity<Review> updateReview(@RequestBody Review review) {
    	Review currentReview = reviewDAO.get(review.getreviewid());
         
        if (currentReview==null) {
            return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
        }
        currentReview.setreviewdate(review.getreviewdate());
        currentReview.setprospect(review.getprospect());
        currentReview.settestdrive(review.gettestdrive());
        currentReview.setclosed(review.getclosed());
        currentReview.setminute(review.getminute());
        currentReview.setreviewby(review.getreviewby());

        reviewDAO.update(currentReview);
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    @RequestMapping(value = ReviewRestURIConstant.Delete, method = RequestMethod.DELETE)
    public ResponseEntity<Review> deleteReview(@RequestBody Review review) {
        if (review == null) {
            return new ResponseEntity<Review>(review, HttpStatus.NOT_FOUND);
        }
        reviewDAO.delete(review.getreviewid());
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    @RequestMapping(value="/listReview", method = RequestMethod.GET)
    public ModelAndView listReview(Principal principal) {
        UserLogin userLogin = userLoginDAO.get(principal.getName());
        String currentPeriod = closingPeriodDAO.getCurrentPeriod(userLogin.getcompanyid());
        List<String> periods = closingPeriodDAO.getPeriod(userLogin.getcompanyid());	
        ModelAndView mav = new ModelAndView("reviewList");
        mav.addObject("currentPeriod", currentPeriod);
        mav.addObject("periods", periods);
        mav.addObject("role", userLogin.getrole());
        Roles role = Roles.valueOf(userLogin.getrole()); 
        switch (role){
        case MD:
            mav.addObject("listReview", reviewDAO.listByCompany(userLogin.getcompanyid()));
            break;
        case MA:
            Branch branch = branchDAO.getByMA(userLogin.getuserid());
            mav.addObject("branch", branch);
            mav.addObject("listReview", reviewDAO.listByBranch(branch.getbranchid()));
            break;
        case TL:
            Team team = teamDAO.getByUser(userLogin.getuserid());
            mav.addObject("team", team);
            mav.addObject("listReview", reviewDAO.listByTeam(team.getteamid()));
            break;
        default:
            mav.addObject("listReview", reviewDAO.list(userLogin.getuserid()));
        }
 	    return mav;
 	}

    @RequestMapping(value = "/addReview", method = RequestMethod.GET)
    public ModelAndView addReview(HttpServletRequest request) {
        int userid = Integer.parseInt(request.getParameter("userid"));
        int targetid = Integer.parseInt(request.getParameter("targetid"));
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        UserLogin userProfile = userLoginDAO.findUser(userid);
        UserTarget userTarget = userTargetDAO.get(targetid);
        String currentPeriod = closingPeriodDAO.getCurrentPeriod(userLogin.getcompanyid());
        Team team = teamDAO.get(userProfile.getteamid());
        Branch branch = branchDAO.get(userProfile.getbranchid());
        Company company = companyDAO.get(userProfile.getcompanyid());
        UserMonthlySummary userMonthlySummary = userMonthlySummaryDAO.get(userTarget.getperiod(), userid, userProfile.getrole()); 
        Review newReview = new Review();
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        newReview.setreviewdate(date);
        newReview.setperiod(userTarget.getperiod());
        newReview.setuserid(userid);
        newReview.setusername(userProfile.getusername());
        newReview.settargetid(userTarget.gettargetid());
        newReview.setteamtargetid(userTarget.getteamtargetid());
        newReview.setteamid(team.getteamid());
        newReview.setteamname(team.getteamname());
        newReview.setbranchid(branch.getbranchid());
        newReview.setbranchname(branch.getbranchname());
        newReview.setcompanyid(company.getcompanyid());
        newReview.setcompanyname(company.getcompanyname());
        newReview.setprospect(userMonthlySummary.getactualprospect());
        newReview.settestdrive(userMonthlySummary.getactualtestdrive());
        newReview.setclosed(userMonthlySummary.getactualclosed());
        newReview.setreviewby(userLogin.getuserid());  
        ModelAndView mav = new ModelAndView("reviewForm");
        mav.addObject("role", userLogin.getrole());
        mav.addObject("team", team);
        mav.addObject("branch", branch);
        mav.addObject("currentPeriod", currentPeriod);
        mav.addObject("userTarget", userTarget);
        mav.addObject("review", newReview);
        return mav;
    }

    @RequestMapping(value = "/editReview", method = RequestMethod.GET)
    public ModelAndView editReview(HttpServletRequest request) {
        int reviewid = Integer.parseInt(request.getParameter("reviewid")); 
        UserLogin userLogin = userLoginDAO.get(request.getUserPrincipal().getName());
        Branch branch = branchDAO.get(userLogin.getbranchid());
        Team team = teamDAO.get(userLogin.getteamid());
        Review editReview = reviewDAO.get(reviewid);
        UserTarget userTarget = userTargetDAO.get(editReview.gettargetid());
        String currentPeriod = closingPeriodDAO.getCurrentPeriod(userLogin.getcompanyid());
        ModelAndView mav = new ModelAndView("reviewForm");
        mav.addObject("role", userLogin.getrole());        
        mav.addObject("team", team);
        mav.addObject("branch", branch);
        mav.addObject("currentPeriod", currentPeriod);
        mav.addObject("userTarget", userTarget);
        mav.addObject("review", editReview);
        return mav;
    }    
}
