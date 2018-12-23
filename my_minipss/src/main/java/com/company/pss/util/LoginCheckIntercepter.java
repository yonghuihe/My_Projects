package com.company.pss.util;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 检查登录拦截器
 * @author yonghui
 *
 */
public class LoginCheckIntercepter extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	/**
	 * 从session中获取用户，
	 * 	判断是否为null，如果为null调到登录页面
	 * 				如果不为null，放行
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		if (ActionContext.getContext().getSession().get("USERINSESSION") != null){
			invocation.invoke();
		}
		return Action.LOGIN;
	}

}
