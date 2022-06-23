/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.login.springdemo.entity.User;
import com.login.springdemo.service.UserService;

@Service
public class MyUserDetailService implements UserDetailsService {
	
	
	@Autowired
	UserService userService;

	/**
	 *Fetch the user as per username in database  
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.getUserByUsername(username);
		if (null==user)
			throw new UsernameNotFoundException(username + " Not found");
		return new UserDetailsImpl(user);
	}

}
