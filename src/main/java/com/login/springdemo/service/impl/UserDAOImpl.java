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
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.login.springdemo.dao.UserDAO;
import com.login.springdemo.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 *get the list of users
	 */
	@Override
	public List<User> getUser() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		Query<User> theQuery = currentSession.createQuery("from User order by lastName", User.class);
		List<User> users = theQuery.getResultList();

		return users;
	}

	/**
	 *save the user
	 */
	@Override
	public void saveUser(User theUser) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/upate the User ... finally 
		currentSession.saveOrUpdate(theUser);
	}

	/**
	 *delete the user based on the id
	 */
	@Override
	public void deleteUser(Long theId) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		Query<?> theQuery = currentSession.createQuery("delete from User where id=:userId");
		theQuery.setParameter("userId", theId);

		theQuery.executeUpdate();

	}

	/**
	 *get the user based on the id 
	 */
	@Override
	public User getUserById(Long theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		User theUser = currentSession.get(User.class, theId);

		return theUser;

	}

	/**
	 *get the user based on the username 
	 */
	@Override
	public User getUserByUsername(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		NativeQuery<User> query = currentSession.createNativeQuery("select * from user where username=:username",
				User.class);
		query.setParameter("username", username);
		User user = query.getResultList().isEmpty() ? null : query.getResultList().get(0);
		return user;
	}

	/**
	 *get the not login user list based on id 
	 */
	@Override
	public List<User> getNotLoginUserList(Long theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		NativeQuery<User> query = currentSession.createNativeQuery("select * from user where id!=:id",
				User.class);
		
		query.setParameter("id", theId);
		List<User> notLoginUser = query.getResultList();
		return notLoginUser;
	}

}
