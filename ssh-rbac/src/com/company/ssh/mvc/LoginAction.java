package com.company.ssh.mvc;

import com.company.ssh.domain.Employee;
import com.company.ssh.service.IEmployeeService;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private IEmployeeService employeeService;
	
	public String execute(){
		Employee emp = employeeService.login(userName,password);
		if (emp == null){
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
