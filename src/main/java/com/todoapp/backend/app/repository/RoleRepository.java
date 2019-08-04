package com.todoapp.backend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.backend.app.model.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, Integer> {
	
}
