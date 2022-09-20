package com.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.model.TeacherModel;
import com.studentmanagement.repo.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;
	@Override
	public List<TeacherModel> getAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public TeacherModel addTeacher(TeacherModel teacher) {
		return teacherRepository.save(teacher);
	}

	@Override
	public void deleteTeacher(int id) {
		teacherRepository.deleteById(id);
	}

	@Override
	public TeacherModel getTeacher(int id) {
		return teacherRepository.findById(id).get();
	}

	@Override
	public TeacherModel getTeacherByEmail(String email) {
		// TODO Auto-generated method stub
		return teacherRepository.findByEmail(email);
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return teacherRepository.count();
	}
	
	

}
