package com.project.auditChecklist.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.auditChecklist.CustomErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	Environment env;
	
	@ExceptionHandler(FeignProxyException.class)
	public ResponseEntity<CustomErrorResponse> handleFeignProxyException(FeignProxyException ex) {
		log.info(env.getProperty("string.start"));
		CustomErrorResponse response = new CustomErrorResponse();
		response.setMessage(ex.getMessage());
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(HttpStatus.NOT_FOUND);
		response.setReason(env.getProperty("feign.null"));
		log.info(env.getProperty("string.end"));
		return new ResponseEntity<CustomErrorResponse>(response,HttpStatus.NOT_FOUND);
	}
}
