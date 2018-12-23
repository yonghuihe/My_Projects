package com.company.ssh.service;

import java.util.List;

import com.company.ssh.domain.Department;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;

public interface IDepartmentService {
	void save(Department e);

	void update(Department e);

	void delete(Department e);

	Department get(Long id);

	List<Department> list();
	
	PageResult query(QueryObject qo);
}
