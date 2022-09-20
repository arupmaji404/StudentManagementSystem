package com.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.model.AdminModel;
import com.studentmanagement.repo.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public List<AdminModel> getAllAdmins() {
		
		return adminRepo.findAll();
	}

	@Override
	public AdminModel addAdmin(AdminModel admin) {
		adminRepo.save(admin);
		return admin;
	}

	@Override
	public void deleteAdmin(int id) {
		// TODO Auto-generated method stub
		adminRepo.deleteById(id);
		
	}

//	@Override
//	public AdminModel getAdmin(String email) {
//		return adminRepo.
//	}

	@Override
	public AdminModel getAdmin(int id) {
		return adminRepo.findById(id).get();
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return adminRepo.count();
	}

}
