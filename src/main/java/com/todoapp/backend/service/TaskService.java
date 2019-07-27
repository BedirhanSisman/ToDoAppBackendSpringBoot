package com.todoapp.backend.service;

import java.util.List;

import com.todoapp.backend.entity.Task;

public interface TaskService {
	
	public List<Task> findAll();
	
	public Task findById(String taskName);
	
	public void save(Task task);
	
	public void deleteById(String taskName);

}
