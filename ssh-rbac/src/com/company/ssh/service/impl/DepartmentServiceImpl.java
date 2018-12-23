package com.company.ssh.service.impl;

import java.util.List;

import com.company.ssh.dao.IDepartmentDao;
import com.company.ssh.domain.Department;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;
import com.company.ssh.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {
	private IDepartmentDao DepartmentDao;

	public void setDepartmentDao(IDepartmentDao DepartmentDao) {
		this.DepartmentDao = DepartmentDao;
	}

	@Override
	public void save(Department e) {
		DepartmentDao.save(e);
	}

	@Override
	public void update(Department e) {
		DepartmentDao.update(e);
	}

	@Override
	public void delete(Department e) {
		DepartmentDao.delete(e);
	}

	@Override
	public Department get(Long id) {
		return DepartmentDao.get(id);
	}

	@Override
	public List<Department> list() {
		return DepartmentDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return DepartmentDao.query(qo);
	}

}
