package com.todoapp.backend.app.service;

import java.util.List;

import com.todoapp.backend.app.model.TaskModel;

public interface TaskService {
	
	public List<TaskModel> findAll();
	
	public TaskModel findById(String taskName);
	
	public void save(TaskModel task);
	
	public void deleteById(String taskName);

}
