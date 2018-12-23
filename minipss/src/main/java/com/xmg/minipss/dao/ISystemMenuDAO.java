package com.xmg.minipss.dao;

import java.util.List;

import com.xmg.minipss.domain.Role;
import com.xmg.minipss.domain.SystemMenu;

public interface ISystemMenuDAO extends IGenericDAO<SystemMenu> {

	List<SystemMenu> listChildren();

	/**
	 * //如果当前用户是超级管理员,直接得到parentSn下面所有的菜单就可以了;
	 * @param parentSn
	 */
	List<SystemMenu> loadMenusByParentSn(String parentSn);

	/**
	 * //如果当前用户不是超级管理员,得到当前用户的角色,对应parentSn里面配置给对应角色的菜单;
	 * @param parentSn
	 * @param roles
	 */
	List<SystemMenu> loadMenusByParentSnAndRoles(String parentSn,
			List<Role> roles);

}
