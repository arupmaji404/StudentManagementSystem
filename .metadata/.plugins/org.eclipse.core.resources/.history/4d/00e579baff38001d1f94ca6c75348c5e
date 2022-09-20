package com.studentmanagement.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.model.LoginModel;
import com.studentmanagement.model.TeacherModel;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Integer> {

	public TeacherModel findByEmail(String email);
	
}
