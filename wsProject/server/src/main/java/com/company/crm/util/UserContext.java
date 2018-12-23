package com.company.crm.util;

import javax.servlet.http.HttpServletRequest;

public class UserContext {
	public static final String USERINSSESION = "USER_IN_SSESION";
	
	
	public static ThreadLocal<HttpServletRequest> local =  new ThreadLocal<>();
	
	public static void setRequest(HttpServletRequest request){
		local.set(request);
	}
	
	public static HttpServletRequest getRequest(){
		return local.get();
	}
}
