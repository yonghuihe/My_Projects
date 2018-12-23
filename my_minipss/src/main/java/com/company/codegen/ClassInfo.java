package com.company.codegen;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 模板中需要的一些必要的信息
 * @author Administrator
 *
 */
@Getter
@Setter
public class ClassInfo {

	private String basePackage;
	private String className;
	private List<String> propNames = new ArrayList<String>();

	public ClassInfo(Class<?> clazz) {
		// 解析基础包
		String pkg = clazz.getPackage().getName();
		this.basePackage = pkg.substring(0, pkg.lastIndexOf("."));
		
		// 解析简单类名
		this.className = clazz.getSimpleName();

		//得到属性的名字
		try {
			PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz).getPropertyDescriptors();
			for (PropertyDescriptor pd : pds) {
				propNames.add(pd.getName());
			}
			Collections.reverse(propNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "ClassInfo [basePackage=" + basePackage + ", className="
				+ className + ", propNames=" + propNames + "]";
	}

}
