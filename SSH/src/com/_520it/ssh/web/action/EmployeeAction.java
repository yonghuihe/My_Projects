package com._520it.ssh.web.action;

import java.util.List;

import com._520it.ssh.domain.Employee;
import com._520it.ssh.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import lombok.Setter;

public class EmployeeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Setter
	private IEmployeeService service;
	@Override
	public String execute() throws Exception {
		List<Employee> list = service.list();
		ActionContext.getContext().put("list", list);
		return SUCCESS;
	}
}