package com.company.ssh.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.company.ssh.dao.IEmployeeDao;
import com.company.ssh.domain.Employee;
import com.company.ssh.domain.Permission;
import com.company.ssh.domain.Role;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;
import com.company.ssh.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;

public class EmployeeServiceImpl implements IEmployeeService {
	private IEmployeeDao employeeDao;

	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	public void save(Employee e) {
		employeeDao.save(e);
	}

	@Override
	public void update(Employee e) {
		employeeDao.update(e);
	}

	@Override
	public void delete(Employee e) {
		employeeDao.delete(e);
	}

	@Override
	public Employee get(Long id) {
		return employeeDao.get(id);
	}

	@Override
	public List<Employee> list() {
		return employeeDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return employeeDao.query(qo);
	}

	/**
	 * 1、把登录用户信息查出来放到session中
	 * 2、把登录用户中所有的权限查出来放到session中
	 */
	@Override
	public Employee login(String name, String password) {
		Employee employee = employeeDao.login(name,password);
		if (employee != null){
			ActionContext.getContext().getSession().put("USERINSESSION", employee);
			Set<String> expressions = new HashSet<String>();
			for (Role role : employee.getRoles()) {
				for (Permission p : role.getPermissions()) {
					expressions.add(p.getExpression());
				}
			}
			ActionContext.getContext().getSession().put("EXPRESSIONS", expressions);
		}
		return employee;
	}

}
