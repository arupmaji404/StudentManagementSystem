package com.studentmanagement.service;

import org.springframework.stereotype.Service;

import com.studentmanagement.model.MarksModel;

@Service
public interface MarksService {

	public MarksModel addMarks(MarksModel marks);
	public MarksModel findMarksByEmail(String email);
//	public List<MarksModel> getAllMarksByClass(int class);
	public void deleteMarks(MarksModel marks);
}
