package com.todoapp.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.backend.entity.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, String> {

}
