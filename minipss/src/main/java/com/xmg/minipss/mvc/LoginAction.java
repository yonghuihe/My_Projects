package com.xmg.minipss.mvc;

import com.xmg.minipss.domain.Employee;
import com.xmg.minipss.service.IEmployeeService;

public class LoginAction extends BaseAction {
	private String userName;
	private String password;
	private IEmployeeService employeeService;

	public String login() {
		Employee emp = this.employeeService.login(userName, password);
		if (emp == null) {
			this.addActionMessage("用户名或者密码错误!");
			return LOGIN;
		}
		return SUCCESS;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

}
