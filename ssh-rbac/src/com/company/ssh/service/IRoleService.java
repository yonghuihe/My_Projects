package com.company.ssh.service;

import java.util.List;

import com.company.ssh.domain.Role;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;

public interface IRoleService {
	void save(Role r);

	void update(Role r);

	void delete(Role r);

	Role get(Long id);

	List<Role> list();
	
	PageResult query(QueryObject qo);
}
