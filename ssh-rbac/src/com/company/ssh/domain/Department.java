package com.company.ssh.domain;

public class Department extends BaseDomain {
	private static final long serialVersionUID = 1L;
	/**
	 * 部门名称
	 */
	private String name;
	/**
	 * 部门编号
	 */
	private String sn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", sn=" + sn + "]";
	}

}
