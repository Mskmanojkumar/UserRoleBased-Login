/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.login.springdemo.dto.PasswordChangeDto;
import com.login.springdemo.entity.Role;
import com.login.springdemo.entity.User;
import com.login.springdemo.service.RoleService;
import com.login.springdemo.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleSerivce;

	/**
	 * @param theModel  model attribute to bind form data
	 * @return a string for display jsp page 
	 */
	@GetMapping("/list")
	public String listCustomers(Model theModel) {

		// get User from the service
		List<User> theUser = userService.getUser();

		// add the User to the model
		theModel.addAttribute("users", theUser);

		return "list-users";
	}

	/**
	 * @param theModel model attribute to bind form data
	 * @return a string for display jsp page 
	 */
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		User theUser = new User();
		List<Role> theRole = roleSerivce.getRole();
		theModel.addAttribute("role", theRole);
		theModel.addAttribute("user", theUser);

		return "user-form";
	}

	/**
	 * @param theUser getting the user date from model attribute
	 * @param servletReuest getting the role 
	 * @return redirect to user list 
	 */
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User theUser, HttpServletRequest servletReuest) {

		// save the customer using our service
		Long id = Long.parseLong(servletReuest.getParameter("role"));
		Role theRole = roleSerivce.getRoleById(id);
		if (theRole == null) {
			throw new NoSuchElementException("Invaild Role");
		}
		theUser.setRole(theRole);
		userService.saveUser(theUser);

		return "redirect:/admin/list";
	}

	/**
	 * @param theId used to get the record based on id 
	 * @param theModel model attribute to pre-populate the form
	 * @return  a string for display jsp page 
	 */
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("userId") Long theId, Model theModel) {

		// get the user from our service
		User theUser = userService.getUserById(theId);
		List<Role> theRole = roleSerivce.getRole();
		theModel.addAttribute("role", theRole);
		// set user as a model attribute to pre-populate the form
		theModel.addAttribute("user", theUser);

		// send over to our form
		return "user-form";
	}

	/**
	 * @param theId used to get the record based on id
	 * @return  a string for display jsp page 
	 */
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("userId") Long theId) {

		// delete the customer

		userService.deleteUser(theId);

		return "redirect:/admin/list";
	}
	
	/**
	 * @param theModel model attribute to bind form data
	 * @return a string for display jsp page 
	 */
	@GetMapping("/addrole")
	public String addRole(Model theModel) {
		// create model attribute to bind form data
		Role theRole = new Role();
		theModel.addAttribute("role", theRole);
		return "addrole";
	}
	
	/**
	 * @param theRole getting data from model attribute 
	 * @return a string for display jsp page
	 */
	@PostMapping("/saveRole")
	public String saveRole(@ModelAttribute("role") Role theRole) {

	
		roleSerivce.saveRole(theRole);

		return "redirect:/admin/showFormForAdd";
	}
	
	/**
	 * @param theId  used to get the record based on id
	 * @param theModel model attribute to bind form data
	 * @return a string for display jsp page
	 */
	@GetMapping("/changePassword")
	public String changePassword(@RequestParam("userId") Long theId,Model theModel) {
		
				// get the user from our service
				User theUser = userService.getUserById(theId);
				theModel.addAttribute("user", theUser);
				return "change-password";
		
	}
	
	/**
	 * @param passwordChangeDto getting form data from model stored in DTO class 
	 * @param theModel model attribute to bind form data
	 * @return a string for display jsp page
	 */
	@PostMapping("/changePassword")
	public String changePasswordValidation(@ModelAttribute("user") PasswordChangeDto passwordChangeDto,Model theModel){
		
		if(passwordChangeDto.getNewPwd().equals(passwordChangeDto.getConfirmPwd())) {
			
			userService.changePwd(passwordChangeDto, passwordChangeDto.getNewPwd());
			return "redirect:/admin/list?success";
			
		}
		
		User theUser = userService.getUserById(passwordChangeDto.getId());
		theModel.addAttribute("user", theUser);
		theModel.addAttribute("error", "error");
		return "change-password";
		
	}
}
