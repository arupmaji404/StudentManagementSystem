package com.studentmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.model.AdminModel;

@Service
public interface AdminService {

	public List<AdminModel> getAllAdmins();
	public AdminModel addAdmin(AdminModel admin);
	public void deleteAdmin(int id);
//	public AdminModel getAdmin(String email);
	public AdminModel getAdmin(int id);
}
