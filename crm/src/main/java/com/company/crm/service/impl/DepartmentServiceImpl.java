package com.company.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Department;
import com.company.crm.mapper.DepartmentMapper;
import com.company.crm.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;

	public int deleteByPrimaryKey(Long id) {
		return departmentMapper.deleteByPrimaryKey(id);
	}

	public int insert(Department record) {
		return departmentMapper.insert(record);
	}

	public Department selectByPrimaryKey(Long id) {
		return departmentMapper.selectByPrimaryKey(id);
	}

	public List<Department> selectAll() {
		return departmentMapper.selectAll();
	}

	public int updateByPrimaryKey(Department record) {
		return departmentMapper.updateByPrimaryKey(record);
	}

	@Override
	public Department selectByName(String contents) {
		return departmentMapper.selectByName(contents);
	}

	@Override
	public void updateState(Long id) {
		departmentMapper.updateState(id);
	}


}
