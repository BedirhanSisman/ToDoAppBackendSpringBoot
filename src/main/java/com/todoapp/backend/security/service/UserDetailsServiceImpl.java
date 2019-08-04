package com.todoapp.backend.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.todoapp.backend.app.model.UserModel;
import com.todoapp.backend.app.repository.UserRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository applicationUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel applicationUser = applicationUserRepository.findByUsername(username); 
		if (applicationUser == null) {
			throw new UsernameNotFoundException(username);
		}
		final List<SimpleGrantedAuthority> authorities = new LinkedList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(applicationUser.getRole().getAuthority()));

		return new User(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
	}

}
