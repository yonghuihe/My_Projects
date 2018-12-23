package com.xmg.minipss.service;

import java.util.List;

import com.xmg.minipss.domain.Role;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public interface IRoleService {
	void save(Role o);

	void update(Role o);

	void delete(Role o);

	Role get(Long id);

	List<Role> list();

	PageResult query(QueryObject qo);
}
