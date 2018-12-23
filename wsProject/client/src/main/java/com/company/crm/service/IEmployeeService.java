package com.company.crm.service;

import java.util.List;

import com.company.crm.domain.Employee;
import com.company.crm.page.PageResult;
import com.company.crm.query.EmployeeQuery;

public interface IEmployeeService {
	int deleteByPrimaryKey(Long id);

	int insert(Employee record);

	Employee selectByPrimaryKey(Long id);

	List<Employee> selectAll();

	int updateByPrimaryKey(Employee record);

	Employee getEmployeeForlogin(String username, String password);

	void updateState(Long id);

	PageResult queryPageResult(EmployeeQuery qo);

	List<Long> getRidByEid(Long id);
}
