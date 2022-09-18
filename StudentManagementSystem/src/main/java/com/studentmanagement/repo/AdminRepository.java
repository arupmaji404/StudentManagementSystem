package com.studentmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.model.AdminModel;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Integer> {
	
}
