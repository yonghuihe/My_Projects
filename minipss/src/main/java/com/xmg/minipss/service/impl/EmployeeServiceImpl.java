package com.xmg.minipss.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.xmg.minipss.dao.IEmployeeDAO;
import com.xmg.minipss.domain.Employee;
import com.xmg.minipss.domain.Permission;
import com.xmg.minipss.domain.Role;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.service.IEmployeeService;
import com.xmg.minipss.util.UserContext;

public class EmployeeServiceImpl implements IEmployeeService {

	private IEmployeeDAO employeeDAO;

	public void setEmployeeDAO(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public Employee login(String name, String password) {
		Employee emp = this.employeeDAO.login(name, password);
		if (emp != null) {
			UserContext.putCurrent(emp);
			Set<String> permissions = new HashSet<String>();
			for (Role r : emp.getRoles()) {
				for (Permission p : r.getPermissions()) {
					permissions.add(p.getExpression());
				}
			}
			ActionContext.getContext().getSession()
					.put("PERMISSIONS", permissions);
		}
		return emp;
	}

	@Override
	public void save(Employee o) {
		this.employeeDAO.save(o);
	}

	@Override
	public void update(Employee o) {
		this.employeeDAO.update(o);
	}

	@Override
	public void delete(Employee o) {
		this.employeeDAO.delete(o);
	}

	@Override
	public Employee get(Long id) {
		return this.employeeDAO.get(id);
	}

	@Override
	public List<Employee> list() {
		return this.employeeDAO.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.employeeDAO.query(qo);
	}

	@Override
	public void deleteBatch(List<Long> ids) {
		this.employeeDAO.deleteBatch(ids);
	}

}
