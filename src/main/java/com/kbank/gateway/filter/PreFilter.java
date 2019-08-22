package com.kbank.gateway.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


public class PreFilter extends ZuulFilter {
	 
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @Override
    public String filterType() {
        return "pre";
    }
 
    @Override
    public int filterOrder() {
        return 1;
    }
 
    @Override
    public boolean shouldFilter() {
        return true;
    }
 
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
 
        System.out.println(request.getParameter("name"));
        System.out.println(request.getParameter("email"));
        System.out.println(request.getParameter("password"));
        try {
			request.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        log.info("!@#$PreFilter Request Method : " + request.getMethod());
        log.info("!@#$PreFilter Request URL : " + request.getRequestURL().toString());
        
 
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!validateToken(authorizationHeader)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("API key not authorized");
            ctx.getResponse().setHeader("Content-Type", "text/plain;charset=UTF-8");
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
    
    private boolean validateToken(String tokenHeader) {
        // do something to validate the token
    	
    	System.out.println("!@#$tokenHeader");
    	
        return true;
    }
}

