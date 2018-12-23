package com.company.ssh.dao;

import com.company.ssh.domain.Employee;

public interface IEmployeeDao extends IGenericDao<Employee> {

	Employee login(String username, String password);

}
