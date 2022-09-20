package com.studentmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.model.StudentModel;

@Service
public interface StudentService {

	public List<StudentModel> getAllStudents();
	public StudentModel addStudent(StudentModel student);
	public void deleteStudent(int id);
//	public AdminModel getAdmin(String email);
	public StudentModel getStudent(int id);
	public List<StudentModel> getAllStudentsByStandard(Integer standard);
	public StudentModel getStudentByEmail(String email);
	public long getCount();
}
