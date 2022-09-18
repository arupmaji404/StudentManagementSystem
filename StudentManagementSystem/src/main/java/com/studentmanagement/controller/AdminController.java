package com.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentmanagement.model.AdminModel;
import com.studentmanagement.model.LoginModel;
import com.studentmanagement.model.StudentModel;
import com.studentmanagement.model.TeacherModel;
import com.studentmanagement.service.AdminServiceImpl;
import com.studentmanagement.service.LoginServiceImpl;
import com.studentmanagement.service.StudentServiceImpl;
import com.studentmanagement.service.TeacherServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private LoginServiceImpl loginService;
	
	@Autowired
	private TeacherServiceImpl teacherService;
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@RequestMapping("/adminIndex")
	public String adminIndex()
	{
		return "admin/adminHome";
	}
	
	//Handling the admin control
	
	@RequestMapping("/admin-control")
	public String adminControl(Model model)
	{
		List<AdminModel> admins = adminService.getAllAdmins();
		model.addAttribute("admins", admins);
		return "admin/admincontrol";
	}
	
	@RequestMapping("/addAdminForm")
	public String addAdminForm(Model model)
	{
		System.out.println("Inside add admin form");
		model.addAttribute("admin", new AdminModel());
		return "admin/add-admin-form";
	}
	
	@RequestMapping("/addAdmin")
	public String addAdmin(@ModelAttribute("admin") AdminModel admin, @RequestParam("password") String pass)
	{
		System.out.println(admin);
		System.out.println(pass);
		
		LoginModel loginModel = new LoginModel();
		loginModel.setEmail(admin.getEmail());
		loginModel.setPassword(pass);
		loginModel.setRole("ROLE_ADMIN");
		loginService.addUser(loginModel);
		
		AdminModel adminModel = new AdminModel();
		adminModel.setName(admin.getName());
		adminModel.setEmail(admin.getEmail());
		adminService.addAdmin(adminModel);
		
		
//		return "add-admin-form";
//		return "admin/admincontrol";
		return "redirect:admin-control";
	}
	
	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(@RequestParam("adminId") int id)
	{
		AdminModel admin = adminService.getAdmin(id);
		adminService.deleteAdmin(id);
		LoginModel lm = loginService.findByEmail(admin.getEmail());
		loginService.deleteUser(lm);
		return "redirect:admin-control";
	}
	
	//update admin
	
	@RequestMapping("/showAdminUpdateForm")
	public String showDoctorUpdateForm(@RequestParam int adminId, Model model)
	{
		AdminModel admin = adminService.getAdmin(adminId);
		model.addAttribute("admin", admin);
		return "admin/adminUpdate";
	}
	
	@RequestMapping("/editAdmin")
	public String editAdmin(@ModelAttribute("admin") AdminModel admin)
	{
		System.out.println(admin);
		LoginModel login = loginService.findByEmail((adminService.getAdmin(admin.getId())).getEmail());
		System.out.println(login);
		login.setEmail(admin.getEmail());
		adminService.addAdmin(admin);
		loginService.addUser(login);
		return "redirect:admin-control";
	}
	
	
	
	// Handling the teacher control
	
	@RequestMapping("/teacher-control")
	public String teacherControl(Model model)
	{
		List<TeacherModel> teachers = teacherService.getAllTeachers();
		model.addAttribute("teachers", teachers);
		return "admin/teachercontrol";
	}
	
	@RequestMapping("/addTeacherForm")
	public String addTeacherForm(Model model)
	{
		System.out.println("Inside add teacher form");
		model.addAttribute("teacher", new TeacherModel());
		return "admin/add-teacher-form";
	}
	
	@RequestMapping("/addTeacher")
	public String addTeacher(@ModelAttribute("teacher") TeacherModel teacher, @RequestParam("password") String pass)
	{
		LoginModel loginModel = new LoginModel();
		loginModel.setEmail(teacher.getEmail());
		loginModel.setPassword(pass);
		loginModel.setRole("ROLE_TEACHER");
		loginService.addUser(loginModel);
		
		TeacherModel teacherModel = new TeacherModel();
		teacherModel.setName(teacher.getName());
		teacherModel.setEmail(teacher.getEmail());
		teacherModel.setAddress(teacher.getAddress());
		teacherModel.setQualification(teacher.getQualification());
		teacherModel.setSubject(teacher.getSubject());
		teacherModel.setHerClass(teacher.getHerClass());
		teacherService.addTeacher(teacherModel);
		
		return "redirect:teacher-control";
	}
	
	@RequestMapping("/deleteTeacher")
	public String deleteTeacher(@RequestParam("teacherId") int id)
	{
		TeacherModel teacher = teacherService.getTeacher(id);
		teacherService.deleteTeacher(id);
		LoginModel lm = loginService.findByEmail(teacher.getEmail());
		loginService.deleteUser(lm);
		return "redirect:teacher-control";
	}
	
	// Handling the student control
	@RequestMapping("/student-control")
	public String studentControl(Model model)
	{
		List<StudentModel> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		return "admin/studentcontrol";
	}
	
	@RequestMapping("/addStudentForm")
	public String addStudentForm(Model model)
	{
		System.out.println("Inside add student form");
		model.addAttribute("student", new StudentModel());
		return "admin/add-student-form";
	}
	
	@RequestMapping("/addStudent")
	public String addStudent(@ModelAttribute("student") StudentModel student, @RequestParam("password") String pass)
	{
		LoginModel loginModel = new LoginModel();
		loginModel.setEmail(student.getEmail());
		loginModel.setPassword(pass);
		loginModel.setRole("ROLE_STUDENT");
		loginService.addUser(loginModel);
		
		StudentModel studentModel = new StudentModel();
		studentModel.setName(student.getName());
		studentModel.setRoll(student.getRoll());
		studentModel.setEmail(student.getEmail());
		studentModel.setStandard(student.getStandard());
		studentModel.setAddress(student.getAddress());
		studentModel.setParent(student.getParent());
		studentModel.setPhno(student.getPhno());
		studentModel.setGender(student.getGender());
		studentService.addStudent(studentModel);
		
		return "redirect:student-control";
	}
	
	@RequestMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("studentId") int id)
	{
		StudentModel student = studentService.getStudent(id);
		studentService.deleteStudent(id);
		LoginModel lm = loginService.findByEmail(student.getEmail());
		loginService.deleteUser(lm);
		return "redirect:student-control";
	}
	
	
	
}
