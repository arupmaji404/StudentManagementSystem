package com.studentmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.studentmanagement.model.LoginModel;

@Repository
public interface LoginRepository extends JpaRepository<LoginModel, Integer> {
//	public void deleteByEmail(String email);
//	@Modifying
//	@Query("delete from LoginModel l where l.email=:email")
//	public void deleteByEmail(@Param("email") String email);
	public LoginModel findByEmail(String email);
}
