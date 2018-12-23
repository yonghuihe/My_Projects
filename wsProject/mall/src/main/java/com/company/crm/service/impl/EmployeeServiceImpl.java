package com.company.crm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Employee;
import com.company.crm.mapper.EmployeeMapper;
import com.company.crm.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private EmployeeMapper mapper;
	
	@Override
	public Employee selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	
	@Override
	public Employee getEmployeeForlogin(String username, String password) {
		return mapper.getEmployeeForlogin(username, password);
	}

	
	
}
