package com.company.pss.util;

import java.lang.reflect.Method;
import java.util.Set;

import com.company.pss.domain.Employee;
import com.company.pss.mvc.BaseAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 权限检查拦截器
 * 	1、如果是管理员，直接放行
 * 	2、如果不是管理员
 * 		1、如果方法上没有权限标签，放行
 * 		2、如果方法上有权限标签
 * 			1、如果session中的权限表达式包含方法上的权限表达式，放行
 * 			2、否则拦截：NOPERMISSION
 * @author yonghui
 *
 */
@SuppressWarnings("all")
public class SercurityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Employee current = (Employee) ActionContext.getContext().getSession().get("USERINSESSION");
		if (current.isAdmin()){
			return invocation.invoke();
		} else {
			Class<?> clz = invocation.getProxy().getAction().getClass();
			String methodName = invocation.getProxy().getMethod();
			Method method = clz.getMethod(methodName, null);
			if (method != null && method.isAnnotationPresent(RequiredPermission.class)){
				Set<String> expressions = (Set<String>) ActionContext.getContext().getSession().get("EXPRESSIONS");
				String expression = PermissionUtil.createExpression(method);
				if (expressions.contains(expression)){
					return invocation.invoke();
				}
			} else {
				return invocation.invoke();
			}
		}
		return BaseAction.NO_PERMISSION;
	}

}
