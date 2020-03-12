package com.project.propensib8.rest;

public class UserResponse {
	private Long id;
	private String name;
	private String role;
	private String email;
	
	public UserResponse(Long id, String name, String role, String email) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
