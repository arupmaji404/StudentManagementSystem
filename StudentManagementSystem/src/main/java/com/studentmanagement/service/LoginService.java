package com.studentmanagement.service;

import org.springframework.stereotype.Service;

import com.studentmanagement.model.LoginModel;

@Service
public interface LoginService {
	
	public LoginModel addUser(LoginModel loginModel);
//	public void deleteUserByEmail(String email);
	public LoginModel findByEmail(String email);
	public void deleteUser(LoginModel loginModel);
}
