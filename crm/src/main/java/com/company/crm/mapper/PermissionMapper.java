package com.company.crm.mapper;

import com.company.crm.domain.Employee;
import com.company.crm.domain.Permission;
import com.company.crm.query.PermissionQueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

	List<Permission> getPermissionsByRId(Long rId);

	List<String> selectAllResource();

	Long queryPageResultCount(PermissionQueryObject qo);

	List<Employee> queryPageResult(PermissionQueryObject qo);
}