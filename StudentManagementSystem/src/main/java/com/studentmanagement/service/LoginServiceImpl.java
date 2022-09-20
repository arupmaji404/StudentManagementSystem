package com.studentmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.model.LoginModel;
import com.studentmanagement.repo.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginRepository loginRepository;

	@Override
	public LoginModel addUser(LoginModel loginModel) {
		return loginRepository.save(loginModel);
		
	}

	@Override
	public Optional<LoginModel> findByEmail(String email) {
		return loginRepository.findByEmail(email);
	}

	@Override
	public void deleteUser(LoginModel loginModel) {
		loginRepository.delete(loginModel);
		
	}
	
	

//	@Override
//	public void deleteUserByEmail(String email) {
//		// TODO Auto-generated method stub
//		loginRepository.deleteByEmail(email);
//	}
	
	
}
