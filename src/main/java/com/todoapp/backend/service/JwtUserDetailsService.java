package com.todoapp.backend.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todoapp.backend.entity.UserModel;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private UserService userService;
	//private PasswordEncoder bCryptEncoder;
	
	@Autowired
	public JwtUserDetailsService(UserService userService/*, PasswordEncoder bCryptEncoder*/) {
		this.userService = userService;
		//this.bCryptEncoder = bCryptEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserModel user = userService.findByName(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>() );
	}

}