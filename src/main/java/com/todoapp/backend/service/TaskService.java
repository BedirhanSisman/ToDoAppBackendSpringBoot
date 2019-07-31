package com.todoapp.backend.service;

import java.util.List;

import com.todoapp.backend.entity.TaskModel;

public interface TaskService {
	
	public List<TaskModel> findAll();
	
	public TaskModel findById(String taskName);
	
	public void save(TaskModel task);
	
	public void deleteById(String taskName);

}
