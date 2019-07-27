package com.todoapp.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.backend.entity.Task;

public interface TaskRepository extends JpaRepository<Task, String> {

}
