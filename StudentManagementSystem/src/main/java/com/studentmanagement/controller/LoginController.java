package com.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studentmanagement.model.LoginModel;
import com.studentmanagement.repo.LoginRepository;

@Controller
public class LoginController {
	
	@Autowired
	private LoginRepository loginRepo;
	
	@RequestMapping("/")
	public String home()
	{
		List<LoginModel> loginModels = loginRepo.findAll();
		if(loginModels.isEmpty())
		{
			System.out.println("Inside 0");
			LoginModel loginModel = new LoginModel();
			loginModel.setEmail("admin");
			String password = "admin";
			String encoded = new BCryptPasswordEncoder().encode(password);
			loginModel.setPassword(encoded);
			loginModel.setRole("ROLE_ADMIN");
			loginModel.setActive(true);
			loginRepo.save(loginModel);
		}
		return "home";
	}
	
	
	
	
}
