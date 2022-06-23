/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.login.springdemo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig implements WebMvcConfigurer{
	
	
	
	//set up the variable to hold the properties 
	@Autowired
	private Environment env;
	
	//set up Logger for Diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	
	//Define a Bean ViewReslover
	
	/**
	 * @return -viewResover object to display the jsp page 
	 */
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	/**
	 * @return-BCryptPasswordEncoder for covert the normal string to BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	

    //specify the property file location to registry
    /**
     *used to read resources files (property file)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/"); 
    }	
	
    //define a bean for our security Datasource 
	
	
	/**
	 * @return-execute the database connection reading properties 
	 */
	@Bean
	public DataSource securityDataSource()   {
		
		
		//create connection pool 
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		//set the jdbc class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		
		//log the connection props
		logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.User=" + env.getProperty("jdbc.user"));
		
		
		//set database connections props
		
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//set connection pool props
		
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		
		return securityDataSource;
		
	}
	
	/**
	 * @return reading the hibernate Properties 
	 */
	private Properties getHibernateProperties() {

		// set hibernate properties
		Properties props = new Properties();

		props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		props.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		
		return props;				
	}

	
	//need a helper method 
	//read environment property and convert to int 
	
	/**
	 * @param propName accept propNmae string form
	 * @return read environment property and convert to int
	 */
	private int getIntProperty(String propName) {
		String propVal =env.getProperty(propName);
		
		int intPropval = Integer.parseInt(propVal);
		return intPropval;
	}
	
	/**
	 * @return sessionFactory to maintain the connection 
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		
		// create session factory's
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(securityDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties());
		
		return sessionFactory;
	}
	
	/**
	 * @param sessionFactory to enable the transaction for database
	 * @return after enable the transaction 
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}
	
}
