package com.SpringMVC.dao;

import java.util.List;

import com.SpringMVC.model.UserLogin;
 
public interface UserLoginDAO {
     
    public String getUserRoles(String username);

    public List<String> getAllRoles();

	public UserLogin findUser(int userid);

	public UserLogin findUserEmail(String email);

	public UserLogin get(String username);

	public List<UserLogin> list(String role, int companyid);

    public List<UserLogin> listByTeam(int teamid);

    public List<String> salist();

    public List<String> mdlist();

    public List<String> malist();

    public List<String> leaderlist();

    public List<String> userlist(int teamid);

    public void save(UserLogin userLogin);
    
    public void update(UserLogin userLogin);

    public void delete(String username);

    public boolean isExist(UserLogin userLogin);
}