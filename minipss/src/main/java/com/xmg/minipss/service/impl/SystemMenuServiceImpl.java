package com.xmg.minipss.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xmg.minipss.dao.ISystemMenuDAO;
import com.xmg.minipss.domain.Employee;
import com.xmg.minipss.domain.SystemMenu;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;
import com.xmg.minipss.service.ISystemMenuService;
import com.xmg.minipss.util.UserContext;

public class SystemMenuServiceImpl implements ISystemMenuService {

	private ISystemMenuDAO systemMenuDAO;

	public void setSystemMenuDAO(ISystemMenuDAO systemMenuDAO) {
		this.systemMenuDAO = systemMenuDAO;
	}

	@Override
	public void save(SystemMenu o) {
		this.systemMenuDAO.save(o);
	}

	@Override
	public void update(SystemMenu o) {
		this.systemMenuDAO.update(o);
	}

	@Override
	public void delete(SystemMenu o) {
		this.systemMenuDAO.delete(o);
	}

	@Override
	public SystemMenu get(Long id) {
		return this.systemMenuDAO.get(id);
	}

	@Override
	public List<SystemMenu> list() {
		return this.systemMenuDAO.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.systemMenuDAO.query(qo);
	}

	@Override
	public List<SystemMenu> listChildren() {
		return this.systemMenuDAO.listChildren();
	}

	@Override
	public List<SystemMenu> loadCurrentEmployeeMenus(String parentSn) {
		//分情况
		Employee e = UserContext.getCurrent();
		List<SystemMenu> ret = new ArrayList<SystemMenu>();
		//如果当前用户是超级管理员,直接得到parentSn下面所有的菜单就可以了;
		if (e.isAdmin()) {
			ret = this.systemMenuDAO.loadMenusByParentSn(parentSn);
		} else {
			//如果当前用户不是超级管理员,得到当前用户的角色,对应parentSn里面配置给对应角色的菜单;
			ret = this.systemMenuDAO.loadMenusByParentSnAndRoles(parentSn,
					e.getRoles());
		}
		return ret;
	}

}
