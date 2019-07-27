package com.todoapp.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.backend.dao.TaskRepository;
import com.todoapp.backend.entity.Task;

@Service
public class TaskServiceImpl implements TaskService {
	
	private TaskRepository taskRepository;
	
	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public Task findById(String taskName) {
		
		Optional<Task> result = taskRepository.findById(taskName);
		
		Task task = null;
		
		if(result.isPresent()) {
			task = result.get();
		}else {
			throw new RuntimeException("Didn't find the Task id - " + taskName);
		}
		
		return task;
	}

	@Override
	public void save(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void deleteById(String taskName) {
		taskRepository.deleteById(taskName);
	}

}
