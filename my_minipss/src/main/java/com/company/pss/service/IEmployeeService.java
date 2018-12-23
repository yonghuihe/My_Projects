package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.Employee;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IEmployeeService {
	void save(Employee e);

	void update(Employee e);

	void delete(Employee e);

	Employee get(Long id);

	List<Employee> list();
	
	PageResult query(QueryObject qo);

	Employee login(String name, String password);

	void batchDelete(List<Long> ids);
}
