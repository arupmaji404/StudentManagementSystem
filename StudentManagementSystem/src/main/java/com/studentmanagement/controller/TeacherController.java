package com.studentmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.studentmanagement.model.MarksModel;
import com.studentmanagement.model.StudentModel;
import com.studentmanagement.model.TeacherModel;
import com.studentmanagement.service.MarksService;
import com.studentmanagement.service.StudentService;
import com.studentmanagement.service.TeacherService;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@Autowired
	StudentService studentService;
	
	@Autowired
	private MarksService marksService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping("/teacherIndex")
	public String home()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		System.out.println(username);
		return "teacher/teacherHome";
	}
	
	@RequestMapping("/upload-marks")
	public String uploadMarks(Model model)
	{
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		TeacherModel teacher = teacherService.getTeacherByEmail(username);
		int teacher_class = teacher.getHerClass();
		List<StudentModel> students = studentService.getAllStudentsByStandard(teacher_class);
		model.addAttribute("students", students);
		return "teacher/upload-marks";
	}
	
	@RequestMapping("/giveStudentMarks")
	public String giveMarks(@RequestParam int studentId, @RequestParam String studentEmail, Model model)
	{
//		System.out.println(studentId + " " +  studentEmail);
//		System.out.println("inside give student marks");
		MarksModel marks = marksService.findMarksByEmail(studentEmail);
//		System.out.println("Marks" + marks);
		if(marks == null)
		{
			System.out.println("inside add marks");
			model.addAttribute("marks", new MarksModel());
			model.addAttribute("studentEmail", studentEmail);
			
			return "teacher/add-marks";
		}
		else
		{
			System.out.println("inside update marks");
			model.addAttribute("marks", marks);
			return "teacher/update-marks";
		}
		
	}
	@RequestMapping("/addStudentMarks")
	public String addStudentMarks(@ModelAttribute("marks") MarksModel marks, @RequestParam("email") String email)
	{
		System.out.println(marks);
		System.out.println(email);
		marksService.addMarks(marks);
		return "redirect:/teacher/upload-marks";
	}
	
	@RequestMapping("/updateMarks")
	public String updateStudentMarks(@ModelAttribute("marks") MarksModel marks)
	{
		System.out.println(marks);
		System.out.println("Yoooo");
//		System.out.println(email);
		marksService.addMarks(marks);
		return "redirect:/teacher/upload-marks";
	}
	
	@RequestMapping("/view-marks")
	public String viewClassMarks(Model model)
	{
//		List<MarksModel> = marksService.f
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		TeacherModel teacher = teacherService.getTeacherByEmail(username);
		int teacher_class = teacher.getHerClass();
		List<StudentModel> students = studentService.getAllStudentsByStandard(teacher_class);
		List<MarksModel> allMarks = new ArrayList<MarksModel>();
		MarksModel temp = null;
		for (int i = 0; i < students.size(); i++) {
			 temp = marksService.findMarksByEmail(students.get(i).getEmail());
			 if(temp != null)
				 allMarks.add(temp);
        }
		System.out.println(allMarks);
		model.addAttribute("allmarks", allMarks);
		return "teacher/viewclassmarks";
	}
}
