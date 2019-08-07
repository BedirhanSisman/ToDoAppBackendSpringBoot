package com.todoapp.backend.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class UserModel {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@OneToOne(targetEntity = RoleModel.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_role_id")
	private RoleModel role;

	public UserModel() {
	
	}
	
	public UserModel(String username, String password, RoleModel role) {
		this.username = username;
		this.password = password;
		this.role = role; 
	}

	public UserModel(long id, String username, String password, RoleModel role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public UserModel(long id, String uuid, String username, String password, boolean enabled, RoleModel role) {
		this.id = id;
		this.uuid = uuid;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

}
