package com.xmg.minipss.dao;

import java.util.List;

import com.xmg.minipss.domain.Employee;

public interface IEmployeeDAO extends IGenericDAO<Employee> {

	Employee login(String name, String password);

	void deleteBatch(List<Long> ids);
}
