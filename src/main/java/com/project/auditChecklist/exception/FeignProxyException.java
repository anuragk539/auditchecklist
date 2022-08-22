package com.project.auditChecklist.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class FeignProxyException extends Exception {

	private static final long serialVersionUID =1L;
	
	@Autowired
	Environment env;
	
	public FeignProxyException() {
		super();
	}
}
