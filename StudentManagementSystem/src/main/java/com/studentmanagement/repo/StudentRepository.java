package com.studentmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studentmanagement.model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Integer> {

	@Query(value = "SELECT s FROM StudentModel s WHERE s.standard = ?1")
	public List<StudentModel> findAllStudentsByStandard(Integer standard);
	
	public StudentModel findByEmail(String email);
	
	@Query(value = "SELECT COUNT(s) FROM StudentModel s WHERE s.standard = ?1")
	public int countByStandard(Integer standard);
}
