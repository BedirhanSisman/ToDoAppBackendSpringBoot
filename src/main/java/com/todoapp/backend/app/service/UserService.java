package com.todoapp.backend.app.service;

import java.util.List;

import com.todoapp.backend.app.model.RoleModel;
import com.todoapp.backend.app.model.UserModel;

public interface UserService {

	public List<UserModel> findAll();
	
	public UserModel findById(long userId);
	
	public void save(UserModel user);
	
	public void deleteByUsername(String username);
	
	public UserModel findByName(String userName);
	
	public RoleModel findRoleById(int roleId);
	
	public RoleModel getRoleByUsername(String username);
	
}
