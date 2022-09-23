package com.studentmanagement.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.studentmanagement.model.LoginModel;

public class MyUserDetails implements UserDetails {
	
	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails(LoginModel loginModel)
	{
		this.userName = loginModel.getEmail();
		this.password = loginModel.getPassword();
		this.active = loginModel.isActive();
		this.authorities = Arrays.stream(loginModel.getRole().split(","))
							.map(SimpleGrantedAuthority::new)
							.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
