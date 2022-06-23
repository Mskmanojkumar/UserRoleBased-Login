/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.service;

import java.util.List;

import com.login.springdemo.dto.PasswordChangeDto;
import com.login.springdemo.entity.User;

public interface UserService {
	
	public List<User> getUser();

	public void saveUser(User theUser);

	public void deleteUser(Long theId);

	public User getUserById(Long theId);

	User getUserByUsername(String username);
	
	public List<User> getNotLoginUserList(Long theId);
	
	public void changePwd(PasswordChangeDto passwordChangeDto, String newPwd);
}
