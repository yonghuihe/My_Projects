package com.company.ssh.service;

import com.company.ssh.domain.Employee;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;

import java.util.List;

public interface IEmployeeService {
	void save(Employee e);

	void update(Employee e);

	void delete(Employee e);

	Employee get(Long id);

	List<Employee> list();
	
	PageResult query(QueryObject qo);

	Employee login(String name, String password);
}
