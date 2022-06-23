/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.login.springdemo.dao.RoleDAO;
import com.login.springdemo.entity.Role;
import com.login.springdemo.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDAO;

	/**
	 *get the list of roles
	 */
	@Override
	@Transactional
	public List<Role> getRole() {

		return roleDAO.getRole();
	}

	/**
	 *get the role based on id 
	 */
	@Override
	@Transactional
	public Role getRoleById(Long theId) {

		return roleDAO.getRoleById(theId);
	}

	/**
	 *save the role
	 */
	@Override
	@Transactional
	public void saveRole(Role theRole) {
		
			roleDAO.saveRole(theRole);

		
	}

}
