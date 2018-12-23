package com.company.crm.service;

import java.util.List;

import com.company.crm.domain.Permission;

public interface IPermissionService {
	
	int deleteByPrimaryKey(Long id);

	int insert(Permission record);

	Permission selectByPrimaryKey(Long id);

	List<Permission> selectAll();

	int updateByPrimaryKey(Permission record);
	
	List<Permission> getPermissionByRid(Long id);
	
	List<String> getPermissionByEid(Long id);
	
}
