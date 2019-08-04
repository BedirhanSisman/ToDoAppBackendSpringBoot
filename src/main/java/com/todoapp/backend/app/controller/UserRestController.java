package com.todoapp.backend.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.backend.app.model.RoleModel;
import com.todoapp.backend.app.model.UserModel;
import com.todoapp.backend.app.service.UserService;

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
	
	@GetMapping(value = "/list")
	@Secured("ROLE_ADMIN")
	public List<UserModel> findAll() {
		return userService.findAll();
	}
	
	@PostMapping(value = "/register")
	@Secured("ROLE_ADMIN")
	public void register(@RequestBody UserModel user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		user.setRole(userService.findRoleById(1)); 	// Default Role : ROLE_USER
		user.setEnabled(true); 						// Default Value : true
		userService.save(user);
	}
	
	@GetMapping(value = "/{username}")
	public UserModel findUserByName(@PathVariable String username) {
		return userService.findByName(username);
	}
	
	@DeleteMapping(value = "/{username}")
	@Secured("ROLE_ADMIN")
	public void deleteUser(@PathVariable String username) {
		userService.deleteByUsername(username);
	}
	
	@GetMapping(value = "/role/{username}")
	public RoleModel getUserRoleByUsername(@PathVariable String username) {
		return userService.getRoleByUsername(username);
	}
	
}
