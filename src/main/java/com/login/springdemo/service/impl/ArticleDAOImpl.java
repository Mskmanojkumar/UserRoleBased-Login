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

import com.login.springdemo.dao.ArticleDAO;
import com.login.springdemo.entity.Article;


@Repository
public class ArticleDAOImpl implements ArticleDAO{
	
	// need to inject the session factory
		@Autowired
		private SessionFactory sessionFactory;
		
	/**
	 *getting the list of article from database
	 */
	@Override
	public List<Article> getArticle() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Article> theQuery = currentSession.createQuery("from Article", Article.class);
		List<Article> articles = theQuery.getResultList();
		return articles;
	}

	/**
	 *getting the list of articles based on the SenderId
	 */
	@Override
	public List<Article> getArticleBySenderId(Long theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		NativeQuery<Article> query = currentSession.createNativeQuery("select * from article where sender_id=:id",
				Article.class);
		
		query.setParameter("id", theId);
		List<Article> senderArticles = query.getResultList();
		System.out.println(senderArticles);
		return senderArticles;
		
	}

	/**
	 *getting the list of articles based on the ReceiverId
	 */
	@Override
	public List<Article> getArticleByReceiverId(Long theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		NativeQuery<Article> query = currentSession.createNativeQuery("select * from article where recevier_id=:id",
				Article.class);
		
		query.setParameter("id", theId);
		List<Article> recevierArticles = query.getResultList();
		System.out.println(recevierArticles);
		return recevierArticles;
	}

	/**
	 * save the article to database
	 */
	@Override
	public void saveArticle(Article theArticle) {
				// get current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();

				// save/update the article ... finally
				currentSession.saveOrUpdate(theArticle);
		
	}

	/**
	 *delete the article based on id 
	 */
	@Override
	public void deleteArticle(Long theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		NativeQuery<Article> query = currentSession.createNativeQuery("delete from article where id=:id",
				Article.class);
		query.setParameter("id", theId);

		query.executeUpdate();
	}

	/**
	 *get the article based on id 
	 */
	@Override
	public Article getArticleById(Long theId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Article theArticle = currentSession.get(Article.class, theId);

		return theArticle;
	}

}
