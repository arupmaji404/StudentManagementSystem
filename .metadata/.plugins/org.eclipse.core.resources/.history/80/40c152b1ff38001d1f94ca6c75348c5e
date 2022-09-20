package com.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.model.StudentModel;
import com.studentmanagement.repo.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepo;

	@Override
	public List<StudentModel> getAllStudents() {
		return studentRepo.findAll();
	}

	@Override
	public StudentModel addStudent(StudentModel student) {
		return studentRepo.save(student);
	}

	@Override
	public void deleteStudent(int id) {
		studentRepo.deleteById(id);

	}

	@Override
	public StudentModel getStudent(int id) {
		return studentRepo.findById(id).get();
	}

	@Override
	public List<StudentModel> getAllStudentsByStandard(Integer standard) {
		return studentRepo.findAllStudentsByStandard(standard);
	}
	

}
