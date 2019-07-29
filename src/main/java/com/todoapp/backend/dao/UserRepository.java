package com.todoapp.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);

}
