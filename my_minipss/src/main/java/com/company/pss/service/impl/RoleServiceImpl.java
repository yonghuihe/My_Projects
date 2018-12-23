package com.company.pss.service.impl;

import java.util.List;

import com.company.pss.dao.IRoleDao;
import com.company.pss.domain.Role;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;
import com.company.pss.service.IRoleService;

public class RoleServiceImpl implements IRoleService {
	private IRoleDao roleDao;

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void save(Role r) {
		roleDao.save(r);
	}

	@Override
	public void update(Role r) {
		roleDao.update(r);
	}

	@Override
	public void delete(Role r) {
		roleDao.delete(r);
	}

	@Override
	public Role get(Long id) {
		return roleDao.get(id);
	}

	@Override
	public List<Role> list() {
		return roleDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return roleDao.query(qo);
	}

}
