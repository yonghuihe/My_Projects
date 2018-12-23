package com.xmg.minipss.util;

import java.lang.reflect.Method;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.xmg.minipss.domain.Employee;
import com.xmg.minipss.mvc.BaseAction;

public class SecurityCheckInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Employee current = (Employee) ActionContext.getContext().getSession()
				.get("USERINSESSION");
		if (current.isAdmin()) {
			return invocation.invoke();
		} else {
			Class<?> targetClass = invocation.getProxy().getAction().getClass();
			String method = invocation.getProxy().getMethod();
			Method m = targetClass.getMethod(method, null);
			if (m != null && m.isAnnotationPresent(RequiredPermission.class)) {
				String expression = PermissionUtil.createExpression(m);
				Set<String> expressions = (Set<String>) ActionContext
						.getContext().getSession().get("PERMISSIONS");
				if (expressions != null && expressions.contains(expression)) {
					return invocation.invoke();
				}
			} else {
				return invocation.invoke();
			}
		}
		return BaseAction.NOPERMISSION;
	}
}
