package com.company.pss.domain;

import com.opensymphony.xwork2.ActionContext;

/**
 * 获取当前用户
 * @author yonghui
 *
 */
public class UserContext {

	public static Employee getCurrent(){
		return (Employee) ActionContext.getContext().getSession().get("USERINSESSION");
	}
	
	public static void putCurrent(Employee e){
		ActionContext.getContext().getSession().put("USERINSESSION", e);
	}
}
