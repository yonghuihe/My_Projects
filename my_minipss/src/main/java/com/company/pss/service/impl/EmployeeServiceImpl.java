package com.company.pss.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.company.pss.dao.IEmployeeDao;
import com.company.pss.domain.Employee;
import com.company.pss.domain.Permission;
import com.company.pss.domain.Role;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;

public class EmployeeServiceImpl implements IEmployeeService {
	private IEmployeeDao employeeDao;

	public void setEmployeeDao(IEmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public void save(Employee e) {
		employeeDao.save(e);
	}

	public void update(Employee e) {
		employeeDao.update(e);
	}

	public void delete(Employee e) {
		employeeDao.delete(e);
	}

	public Employee get(Long id) {
		return employeeDao.get(id);
	}

	public List<Employee> list() {
		return employeeDao.list();
	}

	public PageResult query(QueryObject qo) {
		return employeeDao.query(qo);
	}

	/**
	 * 1、把登录用户信息查出来放到session中
	 * 2、把登录用户中所有的权限查出来放到session中
	 */
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

	public void batchDelete(List<Long> ids) {
		for (Long id : ids) {
			employeeDao.delete(employeeDao.get(id));
		}
	}

}
