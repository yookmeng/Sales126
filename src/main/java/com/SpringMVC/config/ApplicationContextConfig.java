package com.SpringMVC.config;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.SpringMVC.dao.UserLoginDAO;
import com.SpringMVC.viewresolver.JsonViewResolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.SpringMVC.*")
@EnableTransactionManagement
// Load to Environment.
@PropertySource("classpath:datasource-cfg.postgres.heroku")
//@PropertySource("classpath:datasource-cfg.postgres.local")
//@PropertySource("classpath:datasource-cfg.mssql")
public class ApplicationContextConfig {
 
  // The Environment class serves as the property holder
  // and stores all the properties loaded by the @PropertySource
  @Autowired
  private Environment env;
 
  @SuppressWarnings("unused")
  @Autowired
  private UserLoginDAO userLoginDAO;
 
  @Bean
  public ResourceBundleMessageSource messageSource() {
      ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
      // Load property in message/validator.properties
      rb.setBasenames(new String[] { "messages/validator" });
      return rb;
  }
 
  @Bean
  public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
      ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
      resolver.setContentNegotiationManager(manager);

      // Define all possible view resolvers
      List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
      resolvers.add(jsonViewResolver());
      resolvers.add(jspViewResolver());
      resolver.setViewResolvers(resolvers);
      return resolver;
  }
  
  /*
   * Configure View resolver to provide JSON output using JACKSON library to
   * convert object in JSON format.
   */
  @Bean
  public ViewResolver jsonViewResolver() {
      return new JsonViewResolver();
  }
  
  @Bean
  public ViewResolver jspViewResolver() {
      InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
      viewResolver.setViewClass(JstlView.class);
      viewResolver.setOrder(2);
      viewResolver.setPrefix("/WEB-INF/pages/");
      viewResolver.setSuffix(".jsp");
      return viewResolver;
  }
  
  @Bean(name = "dataSource")
  public DataSource getDataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
 
      // See: datasource-cfg.properties
      dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
      dataSource.setUrl(env.getProperty("ds.url"));
      dataSource.setUsername(env.getProperty("ds.username"));
      dataSource.setPassword(env.getProperty("ds.password"));
      return dataSource;
  }
 
  @Bean(name = "savedRequestAwareAuthenticationSuccessHandler")
  public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
	  SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
	  savedRequestAwareAuthenticationSuccessHandler.setTargetUrlParameter("targetUrl");
	  return savedRequestAwareAuthenticationSuccessHandler;
  }
  
  // Transaction Manager
  @Autowired
  @Bean(name = "transactionManager")
  public DataSourceTransactionManager getTransactionManager(DataSource dataSource) {
      DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
 
      return transactionManager;
  }
}