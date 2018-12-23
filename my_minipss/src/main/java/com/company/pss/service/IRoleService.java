package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.Role;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface IRoleService {
	void save(Role r);

	void update(Role r);

	void delete(Role r);

	Role get(Long id);

	List<Role> list();
	
	PageResult query(QueryObject qo);
}
