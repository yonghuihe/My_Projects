package com.company.crm.service;

import com.company.crm.domain.Employee;

public interface IEmployeeService {

	Employee selectByPrimaryKey(Long id);


	Employee getEmployeeForlogin(String username, String password);


}
