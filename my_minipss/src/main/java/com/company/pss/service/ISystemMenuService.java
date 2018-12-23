package com.company.pss.service;

import java.util.List;

import com.company.pss.domain.SystemMenu;
import com.company.pss.query.PageResult;
import com.company.pss.query.QueryObject;

public interface ISystemMenuService {
	void save(SystemMenu o);

	void update(SystemMenu o);

	void delete(SystemMenu o);

	SystemMenu get(Long id);

	List<SystemMenu> list();

	PageResult query(QueryObject qo);

	List<SystemMenu> getCurrentUserMenuByParent(String parentSn);

	List<SystemMenu> viewParentPath(Long parentId);

	List<SystemMenu> queryMenuChildren(String sn);
}
