package com.studentmanagement.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.studentmanagement.model.LoginModel;
import com.studentmanagement.repo.LoginRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<LoginModel> loginModel = loginRepository.findByEmail(username);
		loginModel.orElseThrow(() -> new UsernameNotFoundException("Not Found: " + username));
		
		return loginModel.map(MyUserDetails::new).get();
	}

}
