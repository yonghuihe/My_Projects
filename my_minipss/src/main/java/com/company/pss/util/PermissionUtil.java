package com.company.pss.util;

import java.lang.reflect.Method;

public class PermissionUtil {

	public static String createExpression(Method m){
		StringBuilder sb = new StringBuilder(100)
				.append(m.getDeclaringClass().getCanonicalName())
				.append(":")
				.append(m.getName());;

		return sb.toString();
	}
	/*public static void main(String[] args) {
		Class clz = EmployeeAction.class;
		Method[] methods = clz.getDeclaredMethods();
		for (Method method : methods) {
			String expression = createExpression(method);
			System.out.println(expression);
		}
	}*/
}
