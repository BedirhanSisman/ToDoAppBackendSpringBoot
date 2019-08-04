package com.todoapp.backend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoapp.backend.app.model.TaskModel;

public interface TaskRepository extends JpaRepository<TaskModel, String> {

}
