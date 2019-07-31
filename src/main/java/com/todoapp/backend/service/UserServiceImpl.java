package com.todoapp.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.backend.dao.UserRepository;
import com.todoapp.backend.entity.UserModel;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
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
	public void deleteById(long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public UserModel findByName(String userName) {
		return userRepository.findByUsername(userName);
	}

}
