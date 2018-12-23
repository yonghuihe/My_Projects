package com.company.crm.mapper;

import java.util.List;

import com.company.crm.domain.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
    
    List<Department> getSimpleDept();
}