/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.login.springdemo.dao.RoleDAO;
import com.login.springdemo.entity.Role;

@Repository
public class RoleDAOImpl implements RoleDAO{
	
	
	// need to inject the session factory
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * get the list of role from database
	 *
	 */
	@Override
	public List<Role> getRole() {
		
			
			Session currentSession = sessionFactory.getCurrentSession();
			Query<Role> theQuery = 
					currentSession.createQuery("from Role",
												Role.class);
			List<Role> role = theQuery.getResultList();
			return role;
		
	}

	/**
	 *get the role record based on id 
	 */
	@Override
	public Role getRoleById(Long theId) {
		

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Role theRole = currentSession.get(Role.class, theId);
		
		return theRole;
	}

	/**
	 * save the Role 
	 */
	@Override
	public void saveRole(Role theRole) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/upate the role ... finally 
		currentSession.saveOrUpdate(theRole);
	}

}
