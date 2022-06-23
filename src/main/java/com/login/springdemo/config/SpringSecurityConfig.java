/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */

package com.login.springdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailService;
	
	/**
	 * @return-for spring security giving the information use DAOauthentication provider
	 */
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	/**
	 *giving access based on HTTP 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/").hasAnyRole("GUEST","ADMIN")
		.antMatchers("/article/**").hasAnyRole("GUEST")
		.antMatchers("/admin/**").hasAnyRole("ADMIN")
		.and().formLogin().permitAll().and().logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	
}