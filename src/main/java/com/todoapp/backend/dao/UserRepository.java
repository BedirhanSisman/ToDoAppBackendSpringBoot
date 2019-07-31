package com.todoapp.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoapp.backend.entity.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	public UserModel findByUsername(String username);

}
