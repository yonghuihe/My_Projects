package com.company.ssh.service;

import java.util.List;

import com.company.ssh.domain.Permission;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;

public interface IPermissionService {

	void delete(Permission e);

	List<Permission> list();
	
	PageResult query(QueryObject qo);
	
	void reload();
}
