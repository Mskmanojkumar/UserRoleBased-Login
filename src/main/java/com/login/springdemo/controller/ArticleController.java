/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */

package com.login.springdemo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.login.springdemo.dto.ArticleDto;
import com.login.springdemo.entity.Article;
import com.login.springdemo.entity.User;
import com.login.springdemo.service.ArticleService;
import com.login.springdemo.service.UserService;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * @param principal used to get the current login user 
	 * @param theModel sent the senderArticlesList using model  
	 * @return a string name for jsp page 
	 */
	@GetMapping("/senderArticlesList")
	public String getNameController(Principal principal,Model theModel) {
		
		String loginUser=principal.getName();
		User user=userService.getUserByUsername(loginUser);
		
		List<Article> senderArticles=articleService.getArticleBySenderId(user.getId());
		
		theModel.addAttribute("senderArticles", senderArticles);
		return "sender-list";
	}

	/**
	 * @param principal  used to get the current login user
	 * @param theModel sent the receivedArticlesList using model 
	 * @return a string name for jsp page 
	 */
	@GetMapping("/receivedArticlesList")
	public String getReceiverArticleList(Principal principal,Model theModel) {
	
		String loginUser=principal.getName();
		
		User user=userService.getUserByUsername(loginUser);
		
		List<Article> receiverArticles=articleService.getArticleByReceiverId(user.getId());
		
		theModel.addAttribute("receiverArticles", receiverArticles);
		return "receiver-list";
	}
	
	
	/**
	 * @param principal principal  used to get the current login user
	 * @param theModel model attribute to bind form data
	 * @return a string name for jsp page
	 */
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Principal principal,Model theModel) {

		// create model attribute to bind form data
		Article theArticle = new Article();
		
		String loginUser=principal.getName();
		User user =userService.getUserByUsername(loginUser);
		List<User>	notLoginUser=userService.getNotLoginUserList(user.getId());
		theModel.addAttribute("user",user);
		theModel.addAttribute("notLoginUser", notLoginUser);
		theModel.addAttribute("article", theArticle);

		return "article-add-form";
	}
	
	/**
	 * @param theArticleDto from model attribute date binding to DTO class
	 * @param theBindingResult 
	 * @return redirect to senderArticlesList
	 */
	@PostMapping("/saveArticle")
	public String saveUser(@ModelAttribute("article") ArticleDto theArticleDto) {

	
		articleService.saveArticle(theArticleDto);
		
		return "redirect:/article/senderArticlesList";
	}
	
	/**
	 * @param theId getting id to delete the record
	 * @return redirect to senderArticlesList
	 */
	@GetMapping("/deleteArticle")
	public String deleteTheArticle(@RequestParam("articleId") Long theId) {

		// delete the Article
		
		articleService.deleteArticle(theId);

		return "redirect:/article/senderArticlesList";
	}
	
	
	/**
	 * @param articleId getting id to update the record
	 * @param theModel  model attribute to bind form data
	 * @param principal  used to get the current login user
	 * @return a string name for display jsp page
	 */
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("articleId") Long articleId, Model theModel,Principal principal) {

		// get the user from our service
		Article theArticle = articleService.getArticleById(articleId);
		String loginUser=principal.getName();
		User user = userService.getUserByUsername(loginUser);
		List<User>	notLoginUser=userService.getNotLoginUserList(user.getId());
		theModel.addAttribute("user",user);
		theModel.addAttribute("notLoginUser",notLoginUser);
		theModel.addAttribute("article",theArticle);
		
		return "article-add-form";
	}

}
