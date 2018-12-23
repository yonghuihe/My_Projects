package com.company.pss.domain;

public class Permission {
	
	private Long id;

	/**
	 * 权限名称
	 */
	private String name;
	/**
	 * 权限表达式
	 */
	
	private String expression;
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

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

}
