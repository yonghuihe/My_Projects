package com.company.crm.service;

import java.util.List;

import com.company.crm.domain.Department;

public interface IDepartmentService {
	
	int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    Department selectByName(String contents);

	void updateState(Long id);

}
