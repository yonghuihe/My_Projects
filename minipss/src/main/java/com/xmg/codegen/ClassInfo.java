package com.xmg.codegen;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xmg.minipss.domain.BaseDomain;

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
		this.className = clazz.getSimpleName();
		String pck = clazz.getPackage().getName();
		pck = pck.substring(0, pck.lastIndexOf("."));
		this.basePackage = pck;

		//得到属性的名字
		try {
			PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,
					BaseDomain.class).getPropertyDescriptors();
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
