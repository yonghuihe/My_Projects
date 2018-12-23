package com.company.pss.dao;

import com.company.pss.domain.Employee;

public interface IEmployeeDao extends IGenericDao<Employee> {

	Employee login(String username, String password);

}
