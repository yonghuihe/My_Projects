package com.xmg.minipss.service.impl;

import java.util.List;

import com.xmg.minipss.dao.IDepartmentDAO;
import com.xmg.minipss.domain.Department;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.service.IDepartmentService;

public class DepartmentServiceImpl implements IDepartmentService {

	private IDepartmentDAO departmentDAO;

	public void setDepartmentDAO(IDepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}

	@Override
	public void save(Department o) {
		this.departmentDAO.save(o);
	}

	@Override
	public void update(Department o) {
		this.departmentDAO.update(o);
	}

	@Override
	public void delete(Department o) {
		this.departmentDAO.delete(o);
	}

	@Override
	public Department get(Long id) {
		return this.departmentDAO.get(id);
	}

	@Override
	public List<Department> list() {
		return this.departmentDAO.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.departmentDAO.query(qo);
	}

}
