package com.todoapp.backend.controller;

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

import com.todoapp.backend.entity.Task;
import com.todoapp.backend.service.TaskService;

@CrossOrigin // Angular Client'ından erişime izin vermek için..
@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {
	
	private TaskService taskService;
	
	@Autowired
	public TaskRestController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping("/list")
	public List<Task> getAllTasks() {
		return taskService.findAll();
	}
	
	@GetMapping("/list/{taskName}")
	public Task getTaskByName(@PathVariable String taskName) {
		return taskService.findById(taskName);
	}
	
	@PostMapping("/list")
	public Task addTask(@RequestBody Task newTask) {
		Task taskForAdd = newTask;
		taskService.save(taskForAdd);;
		return taskForAdd;
	}
	
	@PutMapping("/list")
	public Task updateTask(@RequestBody Task newTask) {
		Task taskForAdd = newTask;
		taskService.save(taskForAdd);;
		return taskForAdd;
	}
	
	@DeleteMapping("/list/{taskName}")
	public void deleteTask(@PathVariable String taskName) {
		taskService.deleteById(taskName);
	}
	
}
