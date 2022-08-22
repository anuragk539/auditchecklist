package com.project.auditChecklist.controller;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.crypto.engines.ISAACEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.auditChecklist.AuditType;
import com.project.auditChecklist.feignclient.AuthClient;
import com.project.auditChecklist.model.Questions;
import com.project.auditChecklist.repository.questionsService;
import com.project.auditChecklist.service.TokenService;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.This;


@CrossOrigin(origins="*")
@Slf4j
@RestController
public class auditChecklistController {
	
	@Autowired
	private questionsService questionsService;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	Environment env;
	
	@Autowired
	AuthClient authClient;
	
	@GetMapping("/testcheck")
	public String testaudit(){
		return "This is auditchecklist";
		
	}

	@GetMapping("/AuditCheckListQuestions")
	public ResponseEntity<?> AuditCheckListQuestions(@RequestHeader(name="Authorization",required=true)String token,@RequestParam AuditType auditType){
		System.out.println("----------------------->"+token);
		ResponseEntity<?> responseEntity;
		List<Questions> questions = new ArrayList<>();
		if(tokenService.checkTokenValidity(token)){
			System.out.println("In checklist" + auditType.getAuditType());
			try{
				questions = questionsService.getQuestionList(auditType.getAuditType());
			} catch(IndexOutOfBoundsException e) {
				log.error(env.getProperty("string.null.exception")); 
				log.info(env.getProperty("string.end"));
				responseEntity= new ResponseEntity<String>(env.getProperty("string.null"),HttpStatus.INTERNAL_SERVER_ERROR);
				return responseEntity;
			}
			responseEntity = new ResponseEntity<List<Questions>>(questions,HttpStatus.OK);
			log.debug(env.getProperty("string.res"),responseEntity);
			log.info(env.getProperty("string.end"));
			return responseEntity;
		} else {
			log.error(env.getProperty("string.token.exp")); 
			log.info(env.getProperty("string.end"));
			responseEntity= new ResponseEntity<String>(env.getProperty("string.token.exp"),HttpStatus.FORBIDDEN);
			return responseEntity;
		}
	}
}
