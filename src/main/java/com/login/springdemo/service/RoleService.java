/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.service;

import java.util.List;

import com.login.springdemo.entity.Role;

public interface RoleService {

	public List<Role> getRole();

	public Role getRoleById(Long theId);

	public void saveRole(Role theRole);
	

}
