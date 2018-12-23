package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.IDepartmentDao;
import com.company.pss.domain.Department;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {
	private IDepartmentDao DepartmentDao;

	public void setDepartmentDao(IDepartmentDao DepartmentDao) {
		this.DepartmentDao = DepartmentDao;
	}

	public void save(Department e) {
		DepartmentDao.save(e);
	}

	public void update(Department e) {
		DepartmentDao.update(e);
	}

	public void delete(Department e) {
		DepartmentDao.delete(e);
	}

	public Department get(Long id) {
		return DepartmentDao.get(id);
	}

	public List<Department> list() {
		return DepartmentDao.list();
	}

	public PageResult query(QueryObject qo) {
		return DepartmentDao.query(qo);
	}

}
