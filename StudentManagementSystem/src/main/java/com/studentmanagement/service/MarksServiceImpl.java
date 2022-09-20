package com.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studentmanagement.model.MarksModel;
import com.studentmanagement.repo.MarksRepository;

@Service
public class MarksServiceImpl implements MarksService {
	
	@Autowired
	private MarksRepository marksRepo;

	@Override
	public MarksModel addMarks(MarksModel marks) {
		return marksRepo.save(marks);
	}

	@Override
	public MarksModel findMarksByEmail(String email) {
		return marksRepo.findByEmail(email);
	}

	@Override
	public void deleteMarks(MarksModel marks) {
		marksRepo.delete(marks);
		
	}

}
