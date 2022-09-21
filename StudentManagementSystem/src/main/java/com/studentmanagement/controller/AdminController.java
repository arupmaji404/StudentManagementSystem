package com.studentmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentmanagement.model.AdminModel;
import com.studentmanagement.model.LoginModel;
import com.studentmanagement.model.MarksModel;
import com.studentmanagement.model.StudentModel;
import com.studentmanagement.model.TeacherModel;
import com.studentmanagement.service.AdminServiceImpl;
import com.studentmanagement.service.LoginServiceImpl;
import com.studentmanagement.service.MarksServiceImpl;
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
	
	@Autowired
	private MarksServiceImpl marksService;
	
	
	@RequestMapping("/adminIndex")
	public String adminIndex(Model model)
	{
		model.addAttribute("adminCount", adminService.getCount());
		model.addAttribute("teacherCount", teacherService.getCount());
		model.addAttribute("studentCount", studentService.getCount());
		
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
		loginModel.setPassword(new BCryptPasswordEncoder().encode(pass));
		loginModel.setRole("ROLE_ADMIN");
		loginModel.setActive(true);
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
		LoginModel lm = loginService.findByEmail(admin.getEmail()).get();
		loginService.deleteUser(lm);
		return "redirect:admin-control";
	}
	
	//update admin
	
	@RequestMapping("/showAdminUpdateForm")
	public String showAdminUpdateForm(@RequestParam int adminId, Model model)
	{
		AdminModel admin = adminService.getAdmin(adminId);
		model.addAttribute("admin", admin);
		return "admin/adminUpdate";
	}
	
	@RequestMapping("/editAdmin")
	public String editAdmin(@ModelAttribute("admin") AdminModel admin)
	{
		System.out.println(admin);
		LoginModel login = loginService.findByEmail((adminService.getAdmin(admin.getId())).getEmail()).get();
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
	public String addTeacher(@Valid @ModelAttribute("teacher") TeacherModel teacher, BindingResult result, @RequestParam("password") String pass)
	{
		if(result.hasErrors())
		{
			System.out.println(result);
			System.out.println("Error occured");
			return "admin/add-teacher-form";
		}
		LoginModel loginModel = new LoginModel();
		loginModel.setEmail(teacher.getEmail());
		loginModel.setPassword(new BCryptPasswordEncoder().encode(pass));
		loginModel.setRole("ROLE_TEACHER");
		loginModel.setActive(true);
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
		LoginModel lm = loginService.findByEmail(teacher.getEmail()).get();
		loginService.deleteUser(lm);
		return "redirect:teacher-control";
	}
	
	//update teacher
	
	@RequestMapping("/showTeacherUpdateForm")
	public String showTeacherUpdateForm(@RequestParam int teacherId, Model model)
	{
		TeacherModel teacher = teacherService.getTeacher(teacherId);
		model.addAttribute("teacher", teacher);
		return "admin/teacherUpdate";
	}
	
	@RequestMapping("/editTeacher")
	public String editTeacher(@ModelAttribute("teacher") TeacherModel teacher)
	{
		System.out.println(teacher);
		LoginModel login = loginService.findByEmail((teacherService.getTeacher(teacher.getId())).getEmail()).get();
		System.out.println(login);
		login.setEmail(teacher.getEmail());
		teacherService.addTeacher(teacher);
		loginService.addUser(login);
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
		loginModel.setPassword(new BCryptPasswordEncoder().encode(pass));
		loginModel.setRole("ROLE_STUDENT");
		loginModel.setActive(true);
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
		LoginModel lm = loginService.findByEmail(student.getEmail()).get();
		loginService.deleteUser(lm);
		MarksModel mm = marksService.findMarksByEmail(student.getEmail());
		marksService.deleteMarks(mm);
		
		return "redirect:student-control";
	}
	
	//update student
	
		@RequestMapping("/showStudentUpdateForm")
		public String showStudentUpdateForm(@RequestParam int studentId, Model model)
		{
			StudentModel student = studentService.getStudent(studentId);
			model.addAttribute("student", student);
			return "admin/studentUpdate";
		}
		
		@RequestMapping("/editStudent")
		public String editStudent(@ModelAttribute("student") StudentModel student)
		{
			System.out.println(student);
			LoginModel login = loginService.findByEmail((studentService.getStudent(student.getId())).getEmail()).get();
			System.out.println(login);
			login.setEmail(student.getEmail());
			MarksModel marksModel = marksService.findMarksByEmail((studentService.getStudent(student.getId())).getEmail());
			if(marksModel != null)
			{

				marksModel.setEmail(student.getEmail());

				marksService.addMarks(marksModel);
				
			}
			studentService.addStudent(student);
			loginService.addUser(login);
			return "redirect:student-control";
		}
	
	
	
}
