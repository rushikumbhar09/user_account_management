package com.uam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uam.entities.UserAccount;
import com.uam.service.UserAccountService;

import jakarta.persistence.Id;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;

	// Method is used to load index page , binding is available
	// Develop form to capture user data
	// Write bootstrap for ui
	// Model will send data from controller to ui
	// index is a html page. In the index.html thymeleaf and bootstrap is used.
    // default url pattern
	@GetMapping("/")
	public String index(Model model) {

		model.addAttribute("userAccount", new UserAccount());

		return "index";
	}

//	Save user
	// When form data submitted form data coming to this method, write logic to save
	// data to db
	// @ModelAttribute -sending attribute to ui
	// sumit button - when we submit sen request to url apttern with form data in
	// the th:object="abc" abc object
	// controller will received abc object and call service layer methomethod
	@PostMapping("/save-user")
	public String handleSubmitButton(@ModelAttribute("userAccount") UserAccount userAccount, Model model) {

		String msg = userAccountService.saveOrUpdate(userAccount);
		model.addAttribute("msg", msg);
		return "index";
	}

	// method bnded to get request
	// data binded to users key
	// If record is alrady active then it will show deactivate button
	// Responsible to retreive use records from database
	// Based on condition in html file activSw button will be displayed
	@GetMapping("/users")
	public String getUsers(Model model) {
		List<UserAccount> listUserAccounts = userAccountService.getAllUserAccounts();
		model.addAttribute("users", listUserAccounts);
		return "view-users";
	}
	
	@GetMapping("/edit")
	public String editUser(@RequestParam("id") Integer id,Model model) {
		UserAccount userAccount=userAccountService.getUserAccount(id);
		model.addAttribute("userAccount",userAccount);  //sending user data to ui, on ui form fields mapped to user objects
		return "index";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") Integer id) {
		boolean isDeleted= userAccountService.deleteUserAcccount(id);
		return "redirect:/users";
	}
	
	@GetMapping("/status")
	public String statusUpdate(@RequestParam("id")Integer uid,@RequestParam("status")String status) {
	boolean isStatusUpdated=	userAccountService.updateUserAccountStatus(uid, status);
		return "redirect:/users";
	}
}
