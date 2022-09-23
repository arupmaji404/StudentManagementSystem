package com.studentmanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "admin")
public class AdminModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name can't be blank")
	private String name;
	@Column(unique = true, nullable = false)
	@Email
	private String email;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AdminModel() {
		super();
	}
	public AdminModel(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	@Override
	public String toString() {
		return "AdminModel [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
