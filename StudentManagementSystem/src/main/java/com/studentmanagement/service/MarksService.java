package com.studentmanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.studentmanagement.model.MarksModel;

@Service
public interface MarksService {

	public MarksModel addMarks(MarksModel marks);
	public MarksModel findMarksByEmail(String email);
	public List<MarksModel> findMarksByStandard(int standard);

	public void deleteMarks(MarksModel marks);
	public Integer getMinEnglishMarks(int standard);
	public Integer getMaxEnglishMarks(int standard);
	public Integer getAvgEnglishMarks(int standard);
	
	public Integer getMaxMathMarks(int standard);
	public Integer getMinMathMarks(int standard);
	public Integer getAvgMathMarks(int standard);
	
	public Integer getMinScienceMarks(int standard);
	public Integer getMaxScienceMarks(int standard);
	public Integer getAvgScienceMarks(int standard);
	
	public Integer getMinSstMarks(int standard);
	public Integer getMaxSstMarks(int standard);
	public Integer getAvgSstMarks(int standard);
	
	public Integer getMinComputerMarks(int standard);
	public Integer getMaxComputerMarks(int standard);
	public Integer getAvgComputerMarks(int standard);
}
