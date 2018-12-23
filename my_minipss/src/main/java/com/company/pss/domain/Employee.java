package com.company.pss.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Employee {

	private Long id;
	/**
	 * 用户名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 部门
	 */
	private Department dept;
	/**
	 * 是否是超级管理员
	 */
	private Boolean admin;

	/**
	 * 角色
	 */
	private List<Role> roles = new ArrayList<Role>();

	public String getRoleNames() {
		List<String> roleNames = new ArrayList<String>();
		for (Role role : roles) {
			roleNames.add(role.getName());
		}
		return StringUtils.join(roleNames, ",");
	}


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
