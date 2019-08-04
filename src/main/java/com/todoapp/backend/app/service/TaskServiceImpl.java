package com.todoapp.backend.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.backend.app.model.TaskModel;
import com.todoapp.backend.app.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	
	private TaskRepository taskRepository;
	
	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<TaskModel> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public TaskModel findById(String taskName) {
		
		Optional<TaskModel> result = taskRepository.findById(taskName);
		
		TaskModel task = null;
		
		if(result.isPresent()) {
			task = result.get();
		}else {
			throw new RuntimeException("Didn't find the Task id - " + taskName);
		}
		
		return task;
	}

	@Override
	public void save(TaskModel task) {
		taskRepository.save(task);
	}

	@Override
	public void deleteById(String taskName) {
		taskRepository.deleteById(taskName);
	}

}
