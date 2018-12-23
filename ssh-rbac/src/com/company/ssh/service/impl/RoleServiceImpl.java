package com.company.ssh.service.impl;

import java.util.List;

import com.company.ssh.dao.IRoleDao;
import com.company.ssh.domain.Role;
import com.company.ssh.query.PageResult;
import com.company.ssh.query.QueryObject;
import com.company.ssh.service.IRoleService;

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
