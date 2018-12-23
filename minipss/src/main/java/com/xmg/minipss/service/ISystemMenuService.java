package com.xmg.minipss.service;

import java.util.List;

import com.xmg.minipss.domain.SystemMenu;
import com.xmg.minipss.query.PageResult;
import com.xmg.minipss.query.QueryObject;

public interface ISystemMenuService {
	void save(SystemMenu o);

	void update(SystemMenu o);

	void delete(SystemMenu o);

	SystemMenu get(Long id);

	List<SystemMenu> list();

	List<SystemMenu> listChildren();

	PageResult query(QueryObject qo);

	List<SystemMenu> loadCurrentEmployeeMenus(String parentSn);
}
