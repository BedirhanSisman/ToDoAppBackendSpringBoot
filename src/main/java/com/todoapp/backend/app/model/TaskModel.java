package com.todoapp.backend.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class TaskModel {
	
	@Id
	@Column(name = "task_name")
	private String taskName;
	
	@Column(name = "completed")
	private boolean completed;
	
	public TaskModel() {
		
	}
	
	public TaskModel(String taskName, boolean completed) {
		this.taskName = taskName;
		this.completed = completed;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "Task [taskName=" + taskName + ", completed=" + completed + "]";
	}
	
}
