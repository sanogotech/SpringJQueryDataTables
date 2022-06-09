package com.macrosoft.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
 
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	  @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	            Authentication authentication) throws ServletException, IOException {
	 
		  	String userRoleValue = "";
		  	
		    //CollectionUtils.
		    for (GrantedAuthority grantedAuthority :  authentication.getAuthorities()) {
		    	userRoleValue = grantedAuthority.getAuthority();
			}
		   
	         
	        String redirectURL = request.getContextPath();
	         
	        if ("ROLE_ADMIN".equals(userRoleValue)) {
	            redirectURL = "/adminHome";
	        } else if ("ROLE_USER".equals(userRoleValue)) {
	            redirectURL = "/userHome";
	        } 
	         
	        response.sendRedirect(redirectURL);
	         
	    }
	

}
