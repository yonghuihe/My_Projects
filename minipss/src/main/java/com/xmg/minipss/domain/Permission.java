package com.xmg.minipss.domain;

public class Permission extends BaseDomain {

	private String expression;
	private String name;

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
