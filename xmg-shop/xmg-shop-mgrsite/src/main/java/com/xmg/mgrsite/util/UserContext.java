package com.xmg.mgrsite.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.xmg.api.domain.LoginInfo;


public class UserContext {

	public static final String USER_IN_SESSION = "loginInfo";
	public static final String VERIFY_CODE_IN_SESSION = "verifyCode";
	
	private static HttpSession getSession(){
		ServletRequestAttributes  RequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return RequestAttributes.getRequest().getSession();
	}
	
	public static void setCurrentUser(LoginInfo current){
		getSession().setAttribute(USER_IN_SESSION, current);
	}

	public static LoginInfo getCurrentUser(){
		return (LoginInfo) getSession().getAttribute(USER_IN_SESSION);
	}

}





