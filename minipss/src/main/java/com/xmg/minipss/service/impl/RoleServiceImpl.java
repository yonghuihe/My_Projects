package com.xmg.minipss.service.impl;

import java.util.List;

import com.xmg.minipss.dao.IRoleDAO;
import com.xmg.minipss.domain.Role;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.service.IRoleService;

public class RoleServiceImpl implements IRoleService {

	private IRoleDAO roleDAO;

	public void setRoleDAO(IRoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}

	@Override
	public void save(Role o) {
		this.roleDAO.save(o);
	}

	@Override
	public void update(Role o) {
		this.roleDAO.update(o);
	}

	@Override
	public void delete(Role o) {
		this.roleDAO.delete(o);
	}

	@Override
	public Role get(Long id) {
		return this.roleDAO.get(id);
	}

	@Override
	public List<Role> list() {
		return this.roleDAO.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.roleDAO.query(qo);
	}

}
