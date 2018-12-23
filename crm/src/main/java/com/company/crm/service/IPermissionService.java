package com.company.crm.service;

import java.util.List;

import com.company.crm.domain.Permission;
import com.company.crm.page.PageResult;
import com.company.crm.query.PermissionQueryObject;

public interface IPermissionService {

	int deleteByPrimaryKey(Long id);

	int insert(Permission record);

	Permission selectByPrimaryKey(Long id);

	List<Permission> selectAll();

	int updateByPrimaryKey(Permission record);

	List<Permission> getPermissionsByRId(Long rId);

	PageResult queryPageResult(PermissionQueryObject qo);

	void load();

}
