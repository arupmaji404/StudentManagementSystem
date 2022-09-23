package com.studentmanagement.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.studentmanagement.model.LoginModel;

@Service
public interface LoginService {
	
	public LoginModel addUser(LoginModel loginModel);
	public Optional<LoginModel> findByEmail(String email);
	public void deleteUser(LoginModel loginModel);
}
