package com.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studentmanagement.model.MarksModel;
import com.studentmanagement.service.MarksServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private MarksServiceImpl marksService;

	@RequestMapping("/studentIndex")
	public String home()
	{
		return "student/studentHome";
	}
	
	@RequestMapping("/view-marks")
	public String viewMarks(Model model)
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		String email = username;
		MarksModel marks = marksService.findMarksByEmail(email);
		model.addAttribute("marks", marks);
		return "student/viewmarks";
	}
}
