package com.project.auditChecklist.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.auditChecklist.model.Questions;

@Service
public class questionsService {

	@Autowired
	private questionsRepository questionsRepository;
	
	public List<Questions> getQuestionList(String auditType) throws IndexOutOfBoundsException{
		
		return questionsRepository.findByAuditType(auditType);
	}
}
