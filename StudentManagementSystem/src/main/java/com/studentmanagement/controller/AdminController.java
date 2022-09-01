package com.studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	@RequestMapping("/adminIndex")
	public String adminIndex()
	{
		return "adminHome";
	}
	
	@RequestMapping("/admin-control")
	public String adminControl()
	{
		return "admincontrol";
	}
	@RequestMapping("/teacher-control")
	public String teacherControl()
	{
		return "teachercontrol";
	}
	@RequestMapping("/student-control")
	public String studentControl()
	{
		return "studentcontrol";
	}
}
