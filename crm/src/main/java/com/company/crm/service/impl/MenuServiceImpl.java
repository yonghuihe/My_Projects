package com.company.crm.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Menu;
import com.company.crm.mapper.MenuMapper;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;
import com.company.crm.service.IMenuService;
@Service
public class MenuServiceImpl implements IMenuService {
	@Autowired
	private MenuMapper menuMapper;
	
	public int deleteByPrimaryKey(Long id) {
		return menuMapper.deleteByPrimaryKey(id);
	}

	public int insert(Menu record) {
		return menuMapper.insert(record);
	}

	public Menu selectByPrimaryKey(Long id) {
		return menuMapper.selectByPrimaryKey(id);
	}

	public List<Menu> selectAll() {
		return menuMapper.selectAll();
	}

	public int updateByPrimaryKey(Menu record) {
		return menuMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryByConditionPage(QueryObject qo) {
		Long count = menuMapper.queryByCondictionCount(qo);
		if(count<=0){
			return new PageResult(0L, Collections.EMPTY_LIST);
		}
		List<Menu> result = menuMapper.queryByConditionResult(qo);
		PageResult pageResult = new PageResult(count,result);
		return pageResult;
	}

	@Override
	public List<Menu> selectRoot() {
		return menuMapper.selectRoot();
	}
}
