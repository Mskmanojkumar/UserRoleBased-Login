/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.login.springdemo.dao.UserDAO;
import com.login.springdemo.dto.PasswordChangeDto;
import com.login.springdemo.entity.User;
import com.login.springdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional
	public List<User> getUser() {

		return userDAO.getUser();
	}

	@Override
	@Transactional
	public void saveUser(User theUser) {
		
		if(theUser.getId()==null) {
		String encryptedPwd = bCryptPasswordEncoder.encode(theUser.getPassword());
		theUser.setPassword(encryptedPwd);
		userDAO.saveUser(theUser);
		}
		else
			userDAO.saveUser(theUser);
	}

	/**
	 *delete the user based on id 
	 */
	@Override
	@Transactional
	public void deleteUser(Long theId) {

		userDAO.deleteUser(theId);
	}

	/**
	 *get the user based on the id 
	 */
	@Override
	@Transactional
	public User getUserById(Long theId) {

		return userDAO.getUserById(theId);
	}

	/**
	 * get the user name based on username
	 */
	@Override
	@Transactional
	public User getUserByUsername(String username) {
		return userDAO.getUserByUsername(username);
	}

	/**
	 * get the not login user name based on id 
	 */
	@Override
	@Transactional
	public List<User> getNotLoginUserList(Long theId) {
		
		return userDAO.getNotLoginUserList(theId);
	}

	/**
	 * change the password 
	 */
	@Override
	@Transactional
	public void changePwd(PasswordChangeDto passwordChangeDto, String newPwd) {
		User user= userService.getUserById(passwordChangeDto.getId());
		user.setPassword(bCryptPasswordEncoder.encode(newPwd));
		 userDAO.saveUser(user);
		
	}

}
