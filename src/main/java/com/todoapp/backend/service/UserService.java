package com.todoapp.backend.service;

import java.util.List;

import com.todoapp.backend.entity.UserModel;

public interface UserService {

	public List<UserModel> findAll();
	
	public UserModel findById(long userId);
	
	public void save(UserModel user);
	
	public void deleteById(long userId);
	
	public UserModel findByName(String userName);
	
}
