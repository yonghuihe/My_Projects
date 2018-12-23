package com.xmg.minipss.service;

import java.util.List;

import com.xmg.minipss.domain.Permission;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public interface IPermissionService {

	void delete(Permission o);

	List<Permission> list();

	PageResult query(QueryObject qo);
	
	void reload();
}
