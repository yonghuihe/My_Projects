package com.xmg.minipss.util;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.minipss.domain.Employee;

public class UserContext {

	public static Employee getCurrent() {
		return (Employee) ActionContext.getContext().getSession()
				.get("USERINSESSION");
	}

	public static void putCurrent(Employee e) {
		ActionContext.getContext().getSession().put("USERINSESSION", e);
	}
	
}
