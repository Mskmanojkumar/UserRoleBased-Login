/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.service.impl;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.springdemo.dao.ArticleDAO;
import com.login.springdemo.dto.ArticleDto;
import com.login.springdemo.entity.Article;
import com.login.springdemo.entity.User;
import com.login.springdemo.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

	
	@Autowired
	private ArticleDAO articleDAO;
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 *get the list of articles
	 */
	@Override
	@Transactional
	public List<Article> getArticle() {
		
		return articleDAO.getArticle();
	}

	/**
	 *get the article by sender id
	 */
	@Override
	@Transactional
	public List<Article> getArticleBySenderId(Long theId) {
		return articleDAO.getArticleBySenderId(theId);
	}

	/**
	 *get the article by Receiver id
	 */
	@Override
	@Transactional
	public List<Article> getArticleByReceiverId(Long theId) {
		// TODO Auto-generated method stub
		return articleDAO.getArticleByReceiverId(theId);
	}

	/**
	 *save the article
	 */
	@Override
	@Transactional
	public void saveArticle(ArticleDto theArticleDto) {
		
		LocalDate date =LocalDate.parse(theArticleDto.getDeliveredDate());
		if(theArticleDto.getId()==null){
		Article theArticle= new Article();
		User userSender = new User();
		User userReceiver = new User();
		theArticle.setTitle(theArticleDto.getTitle());
		userSender.setId(theArticleDto.getSender());
		theArticle.setSender(userSender);
		userReceiver.setId(theArticleDto.getRecevier());
		theArticle.setRecevier(userReceiver);
		theArticle.setCost(theArticleDto.getCost());
		theArticle.setDeliveredDate(date);
			
		articleDAO.saveArticle(theArticle);
		}
		else {
		Article theArticle = articleService.getArticleById(theArticleDto.getId());
		User userSender = new User();
		User userReceiver = new User();
		theArticle.setTitle(theArticleDto.getTitle());
		userSender.setId(theArticleDto.getSender());
		theArticle.setSender(userSender);
		userReceiver.setId(theArticleDto.getRecevier());
		theArticle.setRecevier(userReceiver);
		theArticle.setCost(theArticleDto.getCost());
		theArticle.setDeliveredDate(date);
		
		articleDAO.saveArticle(theArticle);
		}
	}

	/**
	 * delete the article based on the article id 
	 */
	@Override
	@Transactional
	public void deleteArticle(Long theId) {
		articleDAO.deleteArticle(theId);
		
	}

	/**
	 * get the article by id 
	 */
	@Override
	@Transactional
	public Article getArticleById(Long theId) {
		
		return articleDAO.getArticleById(theId);
	}

}
