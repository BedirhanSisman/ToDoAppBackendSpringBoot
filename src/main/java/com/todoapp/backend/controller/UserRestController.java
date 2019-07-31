package com.todoapp.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.backend.entity.UserModel;
import com.todoapp.backend.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private UserService userService;
	private BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	public UserRestController(UserService userService, BCryptPasswordEncoder bCryptEncoder) {
		this.userService = userService;
		this.bCryptEncoder = bCryptEncoder;
	}
	
	@PostMapping(value = "/register")
	public void register(@RequestBody UserModel user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		userService.save(user);
	}
	
	@GetMapping(value = "/{username}")
	public UserModel findUserByName(@PathVariable String username) {
		return userService.findByName(username);
	}
	
}
