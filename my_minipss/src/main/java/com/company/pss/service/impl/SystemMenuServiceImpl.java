package com.company.pss.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.company.pss.dao.ISystemMenuDao;
import com.company.pss.domain.Employee;
import com.company.pss.domain.Role;
import com.company.pss.domain.SystemMenu;
import com.company.pss.domain.UserContext;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;
import com.company.pss.service.ISystemMenuService;

public class SystemMenuServiceImpl implements ISystemMenuService {

	private ISystemMenuDao systemMenuDao;

	public void setSystemMenuDao(ISystemMenuDao systemMenuDao) {
		this.systemMenuDao = systemMenuDao;
	}

	@Override
	public void save(SystemMenu o) {
		this.systemMenuDao.save(o);
	}

	@Override
	public void update(SystemMenu o) {
		this.systemMenuDao.update(o);
	}

	@Override
	public void delete(SystemMenu o) {
		this.systemMenuDao.delete(o);
	}

	@Override
	public SystemMenu get(Long id) {
		return this.systemMenuDao.get(id);
	}

	@Override
	public List<SystemMenu> list() {
		return this.systemMenuDao.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		return this.systemMenuDao.query(qo);
	}

	@Override
	public List<SystemMenu> getCurrentUserMenuByParent(String parentSn) {
		return null;
	}

	//>系统管理>部门管理
	@Override
	public List<SystemMenu> viewParentPath(Long parentId) {
		List<SystemMenu> parentMenu = new ArrayList<SystemMenu>();
		
		// 0.如果parentId为null
		if(parentId == null){
			return parentMenu;
		}
		// 1.获取当前systemMenu,并放到parentMenu
		SystemMenu systemMenu = systemMenuDao.get(parentId);
		parentMenu.add(systemMenu);
		
		// 2.根据当前systemMenu获取所有的父systemMenu对象
		while (systemMenu.getParent() != null){
			//获取所有的父systemMenu对象
			parentMenu.add(systemMenu.getParent());
			//把当前父菜单赋给当前systemMenu对象
			systemMenu = systemMenu.getParent();
		}
		// 3.反转集合
		Collections.reverse(parentMenu);
		return parentMenu;
	}

	@Override
	public List<SystemMenu> queryMenuChildren(String sn) {
		List<SystemMenu> menus = null;
		//1.获取当前用户
		Employee current = UserContext.getCurrent();
		//2.是否是超级管理员，如果是，将所有的菜单返回
		if(current.isAdmin()){
			menus = systemMenuDao.queryMenuChildren(sn);	
		} else {
			//3.根据用户查询出所有的角色
			List<Role> roles = current.getRoles();
			menus = systemMenuDao.queryMenuChildren(roles,sn);	
		}
		return menus;
	}

}
