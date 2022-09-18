package com.studentmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class TeacherModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String email;
	private String name;
	private String address;
	private String qualification;
	private String subject;
	private int herClass;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getHerClass() {
		return herClass;
	}
	public void setHerClass(int herClass) {
		this.herClass = herClass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "TeacherModel [Id=" + Id + ", email=" + email + ", name=" + name + ", address=" + address
				+ ", qualification=" + qualification + ", subject=" + subject + ", herClass=" + herClass + "]";
	}
	
	
	
	
	
}
