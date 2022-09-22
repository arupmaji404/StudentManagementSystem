package com.studentmanagement.service;

import java.util.List;

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

	@Override
	public List<MarksModel> findMarksByStandard(int standard) {
	
		return marksRepo.findByStandard(standard);
	}

	@Override
	public Integer getMinEnglishMarks(int standard) {
		return marksRepo.minEnglish(standard);
	}

	@Override
	public Integer getMaxEnglishMarks(int standard) {
		return marksRepo.maxEnglish(standard);
	}
	
	@Override
	public Integer getAvgEnglishMarks(int standard) {
		return marksRepo.avgEnglish(standard);
	}
	
	@Override
	public Integer getMinMathMarks(int standard) {
		return marksRepo.minMath(standard);
	}

	@Override
	public Integer getMaxMathMarks(int standard) {
		return marksRepo.maxMath(standard);
	}
	
	@Override
	public Integer getAvgMathMarks(int standard) {
		return marksRepo.avgMath(standard);
	}
	
	@Override
	public Integer getMinScienceMarks(int standard) {
		return marksRepo.minScience(standard);
	}

	@Override
	public Integer getMaxScienceMarks(int standard) {
		return marksRepo.maxScience(standard);
	}
	
	@Override
	public Integer getAvgScienceMarks(int standard) {
		return marksRepo.avgScience(standard);
	}
	
	@Override
	public Integer getMinSstMarks(int standard) {
		return marksRepo.minSst(standard);
	}

	@Override
	public Integer getMaxSstMarks(int standard) {
		return marksRepo.maxSst(standard);
	}
	
	@Override
	public Integer getAvgSstMarks(int standard) {
		return marksRepo.avgSst(standard);
	}
	
	@Override
	public Integer getMinComputerMarks(int standard) {
		return marksRepo.minComputer(standard);
	}

	@Override
	public Integer getMaxComputerMarks(int standard) {
		return marksRepo.maxComputer(standard);
	}
	
	@Override
	public Integer getAvgComputerMarks(int standard) {
		return marksRepo.avgComputer(standard);
	}
	

}
