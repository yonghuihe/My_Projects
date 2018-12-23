package com.company.ssh.domain;

public class Permission extends BaseDomain {
	private static final long serialVersionUID = 1L;
	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 权限表达式
	 */
	private String expression;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public String toString() {
		return "Permission [name=" + name + ", expression=" + expression + "]";
	}

}
