package com.SpringMVC.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.SpringMVC.authentication.MyDBAuthenticationService;

import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    MyDBAuthenticationService myDBAauthenticationService;
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // For User in database.
        auth.userDetailsService(myDBAauthenticationService);
    }

	@Autowired
	DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // The pages does not require login        
        http.authorizeRequests().antMatchers("/", "/login").permitAll();
 
        // /userInfo page requires login as USER.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/home").access("hasAnyRole('ROLE_DEV', 'ROLE_SA', 'ROLE_MD', 'ROLE_MA', 'ROLE_TL', 'ROLE_USER')");

        http.authorizeRequests().antMatchers("/listCompany", "/addCompany").access("hasAnyRole('ROLE_DEV')");
        http.authorizeRequests().antMatchers("/editCompany").access("hasAnyRole('ROLE_DEV', 'ROLE_SA')");

        http.authorizeRequests().antMatchers("/listUser", "/addUser").access("hasAnyRole('ROLE_DEV', 'ROLE_SA')");
        http.authorizeRequests().antMatchers("/editUser").access("hasAnyRole('ROLE_DEV', 'ROLE_SA', 'ROLE_MD', 'ROLE_MA', 'ROLE_TL', 'ROLE_USER')");
        
        http.authorizeRequests().antMatchers("/addClosingPeriod").access("hasAnyRole('ROLE_SA')");
        http.authorizeRequests().antMatchers("/listClosingPeriod", "/editClosingPeriod").access("hasAnyRole('ROLE_SA', 'ROLE_MD')");

        http.authorizeRequests().antMatchers("/listBranch").access("hasAnyRole('ROLE_SA', 'ROLE_MD')");
        http.authorizeRequests().antMatchers("/addBranch").access("hasAnyRole('ROLE_SA')");
        http.authorizeRequests().antMatchers("/editBranch").access("hasAnyRole('ROLE_SA', 'ROLE_MA')");
        http.authorizeRequests().antMatchers("/listTeam").access("hasAnyRole('ROLE_SA', 'ROLE_MD')");
        http.authorizeRequests().antMatchers("/addTeam").access("hasAnyRole('ROLE_SA')");
        http.authorizeRequests().antMatchers("/editTeam").access("hasAnyRole('ROLE_SA', 'ROLE_TL')");
        http.authorizeRequests().antMatchers("/listMember", "/addMember", "/editMember").access("hasAnyRole('ROLE_SA', 'ROLE_MD', 'ROLE_TL')");
        http.authorizeRequests().antMatchers("/listBrand", "/addBrand", "/editBrand").access("hasAnyRole('ROLE_SA')");
        http.authorizeRequests().antMatchers("/listModel", "/addModel", "/editModel").access("hasAnyRole('ROLE_SA')");

        http.authorizeRequests().antMatchers("/listCompanyTarget", "/addCompanyTarget", "/editCompanyTarget").access("hasAnyRole('ROLE_MD')");
        http.authorizeRequests().antMatchers("/listBranchTarget", "/addBranchTarget", "/editBranchTarget").access("hasAnyRole('ROLE_MD')");        
        http.authorizeRequests().antMatchers("/listBranchTargetMA").access("hasAnyRole('ROLE_MA')");
        http.authorizeRequests().antMatchers("/listTeamTarget", "/addTeamTarget", "/editTeamTarget").access("hasAnyRole('ROLE_MA')");        
        http.authorizeRequests().antMatchers("/listTeamTargetTL").access("hasAnyRole('ROLE_TL')");
        http.authorizeRequests().antMatchers("/listUserTarget", "/addUserTarget", "/editUserTarget").access("hasAnyRole('ROLE_TL')");        

        http.authorizeRequests().antMatchers("/addReview").access("hasAnyRole('ROLE_MD', 'ROLE_MA', 'ROLE_TL')");
        http.authorizeRequests().antMatchers("/listReview", "/editReview").access("hasAnyRole('ROLE_MD', 'ROLE_MA', 'ROLE_TL', 'ROLE_USER')");
        http.authorizeRequests().antMatchers("/listNotes", "/editNotes").access("hasAnyRole('ROLE_MD', 'ROLE_MA', 'ROLE_TL')");

        http.authorizeRequests().antMatchers("/listProspects", "/addProspect", "/editProspect").access("hasAnyRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/listContacts", "/addContact", "/editContact").access("hasAnyRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/listActivity", "/listActivities", "/addActivity", "/editActivity").access("hasAnyRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/listNote", "/addNotes").access("hasAnyRole('ROLE_USER')");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        //TEST UPDATE
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
        		.successHandler(savedRequestAwareAuthenticationSuccessHandler())
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/home")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
// this one if enable all api call not working
//              .and().csrf()
    	        // Config for remember me 
                .and().rememberMe()
                	.tokenRepository(persistentTokenRepository())
                	.tokenValiditySeconds(1209600)//2 weeks in seconds
                	.rememberMeParameter("remember")                	
                // Config for Logout Page
                .and().logout()
                	.logoutSuccessUrl("/login?logout")
                	.deleteCookies("JSESSIONID")
                	.clearAuthentication(true);
    }

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
       SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
       auth.setTargetUrlParameter("targetUrl");
       return auth;
	}

}