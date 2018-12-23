package com.company.crm.service;

import java.util.List;

import com.company.crm.domain.Menu;

public interface IMenuService {
	
	int deleteByPrimaryKey(Long id);

	int insert(Menu record);

	Menu selectByPrimaryKey(Long id);

	List<Menu> selectAll();

	int updateByPrimaryKey(Menu record);
	
	 List<Menu> getRootMenu();
}
