package com.sfda.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sfda.entity.Users;
import com.sfda.links.UserLinks;
import com.sfda.service.UsersService;
import com.sfda.util.UserDetailsValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/login")
public class LoginController {

	UsersService usersService;

	public LoginController(UsersService usersService) {
		this.usersService = usersService;
	}

	@PostMapping(path = UserLinks.REGISTER_USER)
	public ResponseEntity<?> registerUser(@RequestBody Users user) {
		log.info("In LoginController#registerUser");
		Users registeredUser = null;
		if(UserDetailsValidator.isValidEmail(user.getEmail()) && UserDetailsValidator.isValidPhone(user.getPhone())) {
			user.setPhone(user.getPhone().replaceAll("[-, ]", ""));
			registeredUser = usersService.saveUser(user);
		}
		return ResponseEntity.ok(registeredUser);
	}

	@PostMapping(path = UserLinks.LOGIN_USER)
	public @ResponseBody Users loginUser(@ModelAttribute("email") String email, String password) {
		log.info("In LoginController#loginUser");
		Users loggedInUser = null;
		if(UserDetailsValidator.isValidEmail(email)) {
			loggedInUser = usersService.findUserById("Juan@Valladares.com", "xtreamteam!5");
			log.info("User " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + " is logged in now.");
		}
		return loggedInUser;
	}

	@PostMapping(path = UserLinks.FORGET_PASSWORD)
	public ResponseEntity<?> resetPassword(@RequestBody Users user) {
		log.info("In LoginController#resetPassword");
		if(UserDetailsValidator.isValidEmail(user.getEmail())) {
			usersService.resetPassword(user);
		}
		return ResponseEntity.ok("Link Sent");
	}
}