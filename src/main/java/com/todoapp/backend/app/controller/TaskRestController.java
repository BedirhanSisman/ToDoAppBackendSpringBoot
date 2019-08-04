package com.todoapp.backend.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoapp.backend.app.model.TaskModel;
import com.todoapp.backend.app.service.TaskService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {
	
	private TaskService taskService;
	
	@Autowired
	public TaskRestController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/list")
	public List<TaskModel> getAllTasks() {
		return taskService.findAll();
	}
	
	@GetMapping("/list/{taskName}")
	public TaskModel getTaskByName(@PathVariable String taskName) {
		return taskService.findById(taskName);
	}
	
	@PostMapping("/list")
	public TaskModel addTask(@RequestBody TaskModel newTask) {
		TaskModel taskForAdd = newTask;
		taskService.save(taskForAdd);;
		return taskForAdd;
	}
	
	@PutMapping("/list")
	public TaskModel updateTask(@RequestBody TaskModel newTask) {
		TaskModel taskForAdd = newTask;
		taskService.save(taskForAdd);;
		return taskForAdd;
	}
	
	@DeleteMapping("/list/{taskName}")
	public void deleteTask(@PathVariable String taskName) {
		taskService.deleteById(taskName);
	}
	
}
