package com.xmg.minipss.service;

import java.util.List;

import com.xmg.minipss.domain.Department;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public interface IDepartmentService {
	void save(Department o);

	void update(Department o);

	void delete(Department o);

	Department get(Long id);

	List<Department> list();

	PageResult query(QueryObject qo);
}
