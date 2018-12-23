package com.company.crm.mapper;

import com.company.crm.domain.Department;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    Department selectByName(@Param("name")String name);

	void updateState(Long id);
}