package com.studentmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studentmanagement.model.MarksModel;
import com.studentmanagement.model.StudentModel;
import com.studentmanagement.service.MarksServiceImpl;
import com.studentmanagement.service.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private MarksServiceImpl marksService;

	@Autowired
	private StudentServiceImpl studentService;

	@RequestMapping("/studentIndex")
	public String home(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		String email = username;
		StudentModel student = studentService.getStudentByEmail(email);
		System.out.println(student);
		model.addAttribute("student", student);
		return "student/studentHome";
	}

	@RequestMapping("/view-marks")
	public String viewMarks(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		String email = username;
		MarksModel marks = marksService.findMarksByEmail(email);
		model.addAttribute("marks", marks);
		return "student/viewmarks";
	}

	@RequestMapping("/view-class-marks")
	public String viewClassMark(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		String email = username;
		StudentModel student = studentService.getStudentByEmail(email);
		int standard = student.getStandard();

		List<StudentModel> students = studentService.getAllStudentsByStandard(standard);
		List<MarksModel> allMarks = new ArrayList<MarksModel>();
		MarksModel temp = null;
		for (int i = 0; i < students.size(); i++) {
			temp = marksService.findMarksByEmail(students.get(i).getEmail());
			if (temp != null)
				allMarks.add(temp);
		}
		int min_english = Integer.MAX_VALUE, max_english = 0, avg_english = 0;
		int min_math = Integer.MAX_VALUE, max_math = 0, avg_math = 0;
		int min_science = Integer.MAX_VALUE, max_science = 0, avg_science = 0;
		int min_sst = Integer.MAX_VALUE, max_sst = 0, avg_sst = 0;
		int min_computer = Integer.MAX_VALUE, max_computer = 0, avg_computer = 0;
		int english = Integer.MAX_VALUE, math = 0, science = 0, sst = 0, computer = 0;
		for(int i = 0; i<allMarks.size(); i++)
		{
			english = allMarks.get(i).getEnglish();
			min_english = Math.min(min_english, english);
			max_english = Math.max(max_english, english);
			avg_english += english;
			
			math = allMarks.get(i).getMath();
			min_math = Math.min(min_math, math);
			max_math = Math.max(max_math, math);
			avg_math += math;
			
			science = allMarks.get(i).getScience();
			min_science = Math.min(min_science, science);
			max_science = Math.max(max_science, science);
			avg_science += science;
			
			sst = allMarks.get(i).getSst();
			min_sst = Math.min(min_sst,sst);
			max_sst = Math.max(max_sst, sst);
			avg_sst += sst;
			
			computer = allMarks.get(i).getComputer();
			min_computer = Math.min(min_computer, computer);
			max_computer = Math.max(max_computer, computer);
			avg_computer += computer;
		}
		avg_english = avg_english/allMarks.size();
		avg_math = avg_math/allMarks.size();
		avg_science = avg_science/allMarks.size();
		avg_sst = avg_sst/allMarks.size();
		avg_computer = avg_computer/allMarks.size();
		
		System.out.println(min_english + " " + max_english + " " + avg_english);
//		System.out.println(avg_english + " " + avg_english);
		model.addAttribute("min_english", min_english);
		model.addAttribute("max_english", max_english);
		model.addAttribute("avg_english", avg_english);
		
		model.addAttribute("min_math", min_math);
		model.addAttribute("max_math", max_math);
		model.addAttribute("avg_math", avg_math);
		
		model.addAttribute("min_science", min_science);
		model.addAttribute("max_science", max_science);
		model.addAttribute("avg_science", avg_science);
		
		model.addAttribute("min_sst", min_sst);
		model.addAttribute("max_sst", max_sst);
		model.addAttribute("avg_sst", avg_sst);
		
		model.addAttribute("min_computer", min_computer);
		model.addAttribute("max_computer", max_computer);
		model.addAttribute("avg_computer", avg_computer);
		
		return "student/view-class-marks";

	}
}
