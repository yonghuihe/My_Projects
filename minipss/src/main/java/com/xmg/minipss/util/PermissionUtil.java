package com.xmg.minipss.util;

import java.lang.reflect.Method;

public class PermissionUtil {

	public static final String createExpression(Method m) {
		StringBuilder sb = new StringBuilder(100)
				.append(m.getDeclaringClass().getCanonicalName()).append(":")
				.append(m.getName());
		return sb.toString();
	}

}
