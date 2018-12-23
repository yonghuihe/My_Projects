package com.xmg.minipss.util;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginCheckInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (UserContext.getCurrent() != null) {
			return invocation.invoke();
		}
		return Action.LOGIN;
	}

}
