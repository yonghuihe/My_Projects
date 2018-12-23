package com.company.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Permission;
import com.company.crm.mapper.PermissionMapper;
import com.company.crm.service.IPermissionService;

@Service
public class PermissionServiceImpl implements IPermissionService{

	@Autowired
	private PermissionMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Permission record) {
		return mapper.insert(record);
	}

	@Override
	public Permission selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Permission> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Permission record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Permission> getPermissionByRid(Long id) {
		return  mapper.getPermissionByRid(id);
	}

	@Override
	public List<String> getPermissionByEid(Long id) {
		return mapper.getPermissionByEid(id);
	}
	
}
