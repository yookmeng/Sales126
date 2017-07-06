package com.SpringMVC.authentication;

import java.util.ArrayList;
import java.util.List;

import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.model.UserLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
@Service
public class MyDBAuthenticationService implements UserDetailsService {
 
    @Autowired
    private UserLoginDAO userLoginDAO;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin userLogin = userLoginDAO.get(username);
 
        if (userLogin == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
         
        // [USER,ADMIN,..]
        String role= userLoginDAO.getUserRoles(username);
         
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        if(role!= null)  {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
            grantList.add(authority);
        }        
         
        UserDetails userDetails = (UserDetails) new User(userLogin.getusername(), //
                userLogin.getpassword(),grantList);
 
        return userDetails;
    }
     
}