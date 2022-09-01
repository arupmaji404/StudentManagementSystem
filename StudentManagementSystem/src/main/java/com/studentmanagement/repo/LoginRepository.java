package com.studentmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.model.LoginModel;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Integer> {

}
