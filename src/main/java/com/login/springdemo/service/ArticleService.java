/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.service;

import java.util.List;

import com.login.springdemo.dto.ArticleDto;
import com.login.springdemo.entity.Article;


public interface ArticleService {
	
	public List<Article> getArticle();
	public List<Article> getArticleBySenderId(Long theId);
	public List<Article> getArticleByReceiverId(Long theId);
	public void saveArticle(ArticleDto theArticleDto);
	public void deleteArticle(Long theId);
	public Article getArticleById(Long theId);
	

}
