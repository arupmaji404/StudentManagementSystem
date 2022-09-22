package com.studentmanagement.controller;

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

		model.addAttribute("min_english", marksService.getMinEnglishMarks(standard));
		model.addAttribute("max_english", marksService.getMaxEnglishMarks(standard));
		model.addAttribute("avg_english", marksService.getAvgEnglishMarks(standard));

		model.addAttribute("min_science", marksService.getMinScienceMarks(standard));
		model.addAttribute("max_science", marksService.getMaxScienceMarks(standard));
		model.addAttribute("avg_science", marksService.getAvgScienceMarks(standard));

		model.addAttribute("min_math", marksService.getMinMathMarks(standard));
		model.addAttribute("max_math", marksService.getMaxMathMarks(standard));
		model.addAttribute("avg_math", marksService.getAvgMathMarks(standard));

		model.addAttribute("min_sst", marksService.getMinSstMarks(standard));
		model.addAttribute("max_sst", marksService.getMaxSstMarks(standard));
		model.addAttribute("avg_sst", marksService.getAvgSstMarks(standard));

		model.addAttribute("min_computer", marksService.getMinComputerMarks(standard));
		model.addAttribute("max_computer", marksService.getMaxComputerMarks(standard));
		model.addAttribute("avg_computer", marksService.getAvgComputerMarks(standard));

		
		return "student/view-class-marks";

	}
}
