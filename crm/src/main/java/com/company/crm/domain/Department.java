package com.company.crm.domain;

public class Department {
	
	private Long id;

	/**
	 * 部门编号
	 */
	private String sn;

	/**
	 * 部门名称
	 */
	private String name;

	/**
	 * 状态
	 */
	private Boolean state;

	/**
	 * 部门经理
	 */
	private Employee manager;

	/**
	 * 上级部门
	 */
	private Department parent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn == null ? null : sn.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

}