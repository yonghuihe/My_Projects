package com.company.crm.domain;

import java.util.Date;

public class Customer {
	
	private Long id;
	/**
	 * 客户姓名
	 */
	private String name;
	/**
	 * 客户年龄
	 */
	private Integer age;
	/**
	 * 客户性别
	 */
	private Integer gender;
	/**
	 * 电话号码
	 */
	private String tel;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * qq
	 */
	private String qq;
	/**
	 * 微信
	 */
	private String wechat;
	/**
	 * 职业
	 */
	private String job;
	/**
	 * 收入水平
	 */
	private String salaryLevel;
	/**
	 * 客户来源
	 */
	private String customerSource;
	/**
	 * 创建时间
	 */
	private Date inputTime;
	/**
	 * 转正时间
	 */
	private Date becomeTime;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 负责人
	 */
	private Employee inChargeUser;
	/**
	 * 创建人
	 */
	private Employee inputUser;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSalaryLevel() {
		return salaryLevel;
	}

	public void setSalaryLevel(String salaryLevel) {
		this.salaryLevel = salaryLevel;
	}

	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public Date getBecomeTime() {
		return becomeTime;
	}

	public void setBecomeTime(Date becomeTime) {
		this.becomeTime = becomeTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Employee getInChargeUser() {
		return inChargeUser;
	}

	public void setInChargeUser(Employee inChargeUser) {
		this.inChargeUser = inChargeUser;
	}

	public Employee getInputUser() {
		return inputUser;
	}

	public void setInputUser(Employee inputUser) {
		this.inputUser = inputUser;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", tel=" + tel
				+ ", email=" + email + ", qq=" + qq + ", wechat=" + wechat + ", job=" + job + ", salaryLevel="
				+ salaryLevel + ", customerSource=" + customerSource + ", inputTime=" + inputTime + ", becomeTime="
				+ becomeTime + ", status=" + status + ", inChargeUser=" + inChargeUser + ", inputUser=" + inputUser
				+ "]";
	}

}
