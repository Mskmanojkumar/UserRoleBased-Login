/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.dao;

import com.login.springdemo.entity.User;
import java.util.List;

public interface UserDAO {
	
	public List<User> getUser();
	 public void saveUser(User theUser);
	 public void deleteUser(Long theId);
	 public User getUserById(Long id);
	 User getUserByUsername(String username);
	 public List<User> getNotLoginUserList(Long theId);
}
