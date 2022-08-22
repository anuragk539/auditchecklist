package com.project.auditChecklist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.project.auditChecklist.AuthResponse;
import com.project.auditChecklist.exception.FeignProxyException;
import com.project.auditChecklist.feignclient.AuthClient;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService{

	@Autowired
	private AuthClient authClient;
	
	@Autowired
	Environment env;
	
	public Boolean checkTokenValidity(String token) {
		log.info(env.getProperty("string.start"));
		log.debug(env.getProperty("string.token"),token);
		try {
			log.debug(env.getProperty("valcheck.success"));
			AuthResponse authResponse = authClient.getValidity(token).getBody();
			if(authResponse==null)
				throw new FeignProxyException();
			
			log.info(env.getProperty("string.end"));
			return authResponse.isValid();
		} catch(FeignProxyException fe) {
			log.debug(env.getProperty("valcheck.fail"));
			log.error(env.getProperty("feign.proxy.exp"),fe);
			log.info(env.getProperty("string.end"));
			return false;
		} catch(FeignException e) {
			log.debug(env.getProperty("valcheck.fail"));
			log.error(env.getProperty("feign.exp"),e);
			log.info(env.getProperty("string.end"));
			return false;
		}
	}
}
