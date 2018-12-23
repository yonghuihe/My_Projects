package com.company.ssh.service.impl;

import com.company.ssh.dao.IDepartmentDao;
import com.company.ssh.domain.Department;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;
import com.company.ssh.service.IDepartmentService;

import java.util.List;

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
