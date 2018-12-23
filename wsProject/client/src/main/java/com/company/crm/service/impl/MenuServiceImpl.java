package com.company.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Menu;
import com.company.crm.mapper.MenuMapper;
import com.company.crm.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService{

	@Autowired
	private MenuMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Menu record) {
		return mapper.insert(record);
	}

	@Override
	public Menu selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Menu> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Menu record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Menu> getRootMenu() {
		return mapper.getRootMenu();
	}


	
}
