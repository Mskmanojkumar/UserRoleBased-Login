/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */

package com.login.springdemo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebDisInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return null;
	}

	/**
	 *Specifying descriptor to load the config file 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class [] {DemoAppConfig.class};
	}

	/**
	 *Specifying the mapping 
	 */
	@Override
	protected String[] getServletMappings() {
		
		return new String [] {"/"};
	}

}
