package com.project.auditChecklist.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.project.auditChecklist.AuthResponse;

@FeignClient(url="${fos.auth}", name="audit-authentication")
public interface AuthClient {

	@GetMapping(value="/validate")
	public ResponseEntity<AuthResponse> getValidity(@RequestHeader("Authorization") String token);
}
