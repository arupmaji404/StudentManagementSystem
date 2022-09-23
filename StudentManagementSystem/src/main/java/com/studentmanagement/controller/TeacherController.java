package com.studentmanagement.controller;

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
	public String home(Model model)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		int standard = teacherService.getTeacherByEmail(username).getHerClass();
		model.addAttribute("standard", standard);
		model.addAttribute("countNoOfStud", studentService.getCountByStandard(standard));
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
		int teacherClass = teacher.getHerClass();
		List<StudentModel> students = studentService.getAllStudentsByStandard(teacherClass);
		model.addAttribute("students", students);
		return "teacher/upload-marks";
	}
	
	@RequestMapping("/giveStudentMarks")
	public String giveMarks(@RequestParam int studentId, @RequestParam String studentEmail, Model model)
	{
		MarksModel marks = marksService.findMarksByEmail(studentEmail);
		if(marks == null)
		{
			model.addAttribute("marks", new MarksModel());
			model.addAttribute("studentEmail", studentEmail);
			
			return "teacher/add-marks";
		}
		else
		{
			model.addAttribute("marks", marks);
			return "teacher/update-marks";
		}
		
	}
	@RequestMapping("/addStudentMarks")
	public String addStudentMarks(@ModelAttribute("marks") MarksModel marks)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		TeacherModel teacher = teacherService.getTeacherByEmail(username);
		int teacherClass = teacher.getHerClass();
		marks.setStandard(teacherClass);
		marksService.addMarks(marks);
		return "redirect:/teacher/upload-marks";
	}
	
	@RequestMapping("/updateMarks")
	public String updateStudentMarks(@ModelAttribute("marks") MarksModel marks)
	{
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
		int teacherClass = teacher.getHerClass();
		
		List<MarksModel> allMarks = marksService.findMarksByStandard(teacherClass);
		model.addAttribute("allmarks", allMarks);
		return "teacher/viewclassmarks";
	}
}
