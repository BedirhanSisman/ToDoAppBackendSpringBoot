package com.todoapp.backend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todoapp.backend.app.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	public UserModel findByUsername(String username);

}
