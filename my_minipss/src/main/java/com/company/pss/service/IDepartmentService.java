package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.Department;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IDepartmentService {
	void save(Department e);

	void update(Department e);

	void delete(Department e);

	Department get(Long id);

	List<Department> list();
	
	PageResult query(QueryObject qo);
}
