/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */

package com.login.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class DemoController {
	
	
	
	/**
	 * @return a string for display jsp page to user
	 */
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}

	//add reust mapping for admin
	
	/**
	 * @return a string for display jsp page
	 */
	@GetMapping("/admin")
	public String showAdmin() {
		return "redirect:/admin/list";
	}
	
	
		/**
		 * @returna string for display jsp page
		 */
		@GetMapping("/access-denied")
		public String showAccessDenied() {
			//add request mapping for / access denaid
			return"access-denied";
		}
		
	
}
