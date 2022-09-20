package com.studentmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.model.MarksModel;

@Repository
public interface MarksRepository extends JpaRepository<MarksModel, Integer>{
	public MarksModel findByEmail(String email);
	
}
