package com.company.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Department;
import com.company.crm.mapper.DepartmentMapper;
import com.company.crm.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
	private DepartmentMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Department record) {
		return mapper.insert(record);
	}

	@Override
	public Department selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Department> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Department record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Department> getSimpleDept() {
		return mapper.getSimpleDept();
	}

	
}
