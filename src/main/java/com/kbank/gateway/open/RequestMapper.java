package com.kbank.gateway.open;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Level;

@RestController @RequestMapping("/gateway")
public class RequestMapper {
	
	static final Logger logger = LoggerFactory.getLogger(RequestMapper.class);
	
	@RequestMapping(value="/uri")
	public String getMethod() {
		System.out.println("!@#$ Web Request~~~!");		
		return "uri"; 
	}
	
}
