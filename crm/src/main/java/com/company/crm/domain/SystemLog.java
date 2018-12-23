package com.company.crm.domain;

import java.util.Date;

public class SystemLog {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 时间
	 */
	private Date opTime;
	/**
	 * 操作ip
	 */
	private String opIp;
	/**
	 * 使用功能：类的全限定名+方法名
	 */
	private String function;
	/**
	 * 记录参数
	 */
	private String param;
	/**
	 * 操作用户
	 */
	private Employee opUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public String getOpIp() {
		return opIp;
	}

	public void setOpIp(String opIp) {
		this.opIp = opIp;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Employee getOpUser() {
		return opUser;
	}

	public void setOpUser(Employee opUser) {
		this.opUser = opUser;
	}

	@Override
	public String toString() {
		return "SystemLog [id=" + id + ", opTime=" + opTime + ", opIp=" + opIp + ", function=" + function + ", param="
				+ param + ", opUser=" + opUser + "]";
	}

}
