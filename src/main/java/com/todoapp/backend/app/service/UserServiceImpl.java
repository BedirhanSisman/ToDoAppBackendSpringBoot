package com.todoapp.backend.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.backend.app.model.RoleModel;
import com.todoapp.backend.app.model.UserModel;
import com.todoapp.backend.app.repository.RoleRepository;
import com.todoapp.backend.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	private RoleRepository roleRespository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRespository) {
		this.userRepository = userRepository;
		this.roleRespository = roleRespository;
	}

	@Override
	public List<UserModel> findAll() {
		return userRepository.findAll();
	}

	@Override
	public UserModel findById(long userId) {
		
		Optional<UserModel> result = userRepository.findById(userId);
		
		UserModel user;
		
		if(result.isPresent()) {
			user = result.get();
		}else {
			throw new RuntimeException("Didn't find the user with id : " + userId);
		}
		
		return user;
	}

	@Override
	public void save(UserModel user) {
		userRepository.save(user);
	}

	@Override
	public void deleteByUsername(String username) {
		UserModel userDelete = userRepository.findByUsername(username);
		userRepository.deleteById(userDelete.getId());
	}

	@Override
	public UserModel findByName(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public RoleModel findRoleById(int roleId) {
		return roleRespository.findById(roleId).get();
	}
	
	@Override
	public RoleModel getRoleByUsername(String username) {
		int roleId = userRepository.findByUsername(username).getRole().getId();
		
		return roleRespository.findById(roleId).get();
	}
	
}
