package com.xmg.minipss.service;

import java.util.List;

import com.xmg.minipss.domain.Employee;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public interface IEmployeeService {
	void save(Employee o);

	void update(Employee o);

	void delete(Employee o);

	Employee get(Long id);

	List<Employee> list();

	PageResult query(QueryObject qo);

	Employee login(String name, String password);

	void deleteBatch(List<Long> ids);
}
