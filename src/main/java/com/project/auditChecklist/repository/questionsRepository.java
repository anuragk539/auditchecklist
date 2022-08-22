package com.project.auditChecklist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.auditChecklist.model.Questions;

public interface questionsRepository extends JpaRepository<Questions,Integer>{
	
	List<Questions> findByAuditType(String i);

}
