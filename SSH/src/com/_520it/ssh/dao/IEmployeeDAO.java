package com._520it.ssh.dao;

import java.util.List;

import com._520it.ssh.domain.Employee;

public interface IEmployeeDAO {
	void save(Employee e);
	void delete(Employee e);
	void update(Employee e);
	
	Employee get(Long id);
	List<Employee> list();
}
