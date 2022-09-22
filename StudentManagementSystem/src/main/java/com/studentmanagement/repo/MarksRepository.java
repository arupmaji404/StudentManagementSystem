package com.studentmanagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.studentmanagement.model.MarksModel;

@Repository
public interface MarksRepository extends JpaRepository<MarksModel, Integer>{
	public MarksModel findByEmail(String email);
	public List<MarksModel> findByStandard(int standard);
	
	@Query(value = "SELECT min(english) FROM MarksModel m where m.standard = :standard")
	public Integer minEnglish(@Param("standard") int stanadard);

	@Query(value = "SELECT max(english) FROM MarksModel m where m.standard = :standard")
	public Integer maxEnglish(@Param("standard") int stanadard);
	
	@Query(value = "SELECT avg(english) FROM MarksModel m where m.standard = :standard")
	public Integer avgEnglish(@Param("standard") int stanadard);
	
	@Query(value = "SELECT min(science) FROM MarksModel m where m.standard = :standard")
	public Integer minScience(@Param("standard") int stanadard);

	@Query(value = "SELECT max(science) FROM MarksModel m where m.standard = :standard")
	public Integer maxScience(@Param("standard") int stanadard);
	
	@Query(value = "SELECT avg(science) FROM MarksModel m where m.standard = :standard")
	public Integer avgScience(@Param("standard") int stanadard);
	
	@Query(value = "SELECT min(math) FROM MarksModel m where m.standard = :standard")
	public Integer minMath(@Param("standard") int stanadard);

	@Query(value = "SELECT max(math) FROM MarksModel m where m.standard = :standard")
	public Integer maxMath(@Param("standard") int stanadard);
	
	@Query(value = "SELECT avg(math) FROM MarksModel m where m.standard = :standard")
	public Integer avgMath(@Param("standard") int stanadard);
	
	@Query(value = "SELECT min(sst) FROM MarksModel m where m.standard = :standard")
	public Integer minSst(@Param("standard") int stanadard);

	@Query(value = "SELECT max(sst) FROM MarksModel m where m.standard = :standard")
	public Integer maxSst(@Param("standard") int stanadard);
	
	@Query(value = "SELECT avg(sst) FROM MarksModel m where m.standard = :standard")
	public Integer avgSst(@Param("standard") int stanadard);
	
	@Query(value = "SELECT min(computer) FROM MarksModel m where m.standard = :standard")
	public Integer minComputer(@Param("standard") int stanadard);

	@Query(value = "SELECT max(computer) FROM MarksModel m where m.standard = :standard")
	public Integer maxComputer(@Param("standard") int stanadard);
	
	@Query(value = "SELECT avg(computer) FROM MarksModel m where m.standard = :standard")
	public Integer avgComputer(@Param("standard") int stanadard);
	
}
