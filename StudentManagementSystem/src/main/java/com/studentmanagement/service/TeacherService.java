package com.studentmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.model.TeacherModel;

@Service
public interface TeacherService {
	public List<TeacherModel> getAllTeachers();
	public TeacherModel addTeacher(TeacherModel teacher);
	public void deleteTeacher(int id);
	public TeacherModel getTeacher(int id);
	public TeacherModel getTeacherByEmail(String email);
	public long getCount();
}
