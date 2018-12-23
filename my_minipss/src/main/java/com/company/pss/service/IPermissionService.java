package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.Permission;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IPermissionService {

	void delete(Permission e);

	List<Permission> list();
	
	PageResult query(QueryObject qo);
	
	void reload();
}
