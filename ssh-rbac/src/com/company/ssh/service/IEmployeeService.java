package com.company.ssh.service;

import java.util.List;

import com.company.ssh.domain.Employee;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;

public interface IEmployeeService {
	void save(Employee e);

	void update(Employee e);

	void delete(Employee e);

	Employee get(Long id);

	List<Employee> list();
	
	PageResult query(QueryObject qo);

	Employee login(String name, String password);
}
