package com._520it.ssh.service.impl;

import java.util.List;

import com._520it.ssh.dao.IEmployeeDAO;
import com._520it.ssh.domain.Employee;
import com._520it.ssh.service.IEmployeeService;

import lombok.Setter;

public class EmployeeServiceImpl implements IEmployeeService {
	@Setter
	private IEmployeeDAO dao;//没有业务,调用dao
	@Override
	public void save(Employee e) {
		dao.save(e);
//		System.out.println(1/0);
	}
	
	@Override
	public void delete(Employee e) {
		dao.delete(e);
	}

	@Override
	public void update(Employee e) {
		dao.update(e);
	}

	@Override
	public Employee get(Long id) {
		return dao.get(id);
	}

	@Override
	public List<Employee> list() {
		return dao.list();
	}
}
