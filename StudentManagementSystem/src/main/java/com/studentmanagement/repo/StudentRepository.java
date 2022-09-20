package com.studentmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studentmanagement.model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	@Query(value = "SELECT s FROM StudentModel s where s.standard = ?1")
	public List<StudentModel> findAllStudentsByStandard(Integer standard);
}
