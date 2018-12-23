package com.company.crm.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.crm.domain.Permission;
import com.company.crm.domain.Role;
import com.company.crm.mapper.RoleMapper;
import com.company.crm.page.PageResult;
import com.company.crm.query.QueryObject;
import com.company.crm.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	private RoleMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Role record) {
		int count = mapper.insert(record);
		//关联关系(角色与权限)
		List<Permission> permissions = record.getPermissions();
		for (Permission permission : permissions) {
			mapper.insertRelation(record.getId(), permission.getId());
		}
		
		return count;
	}

	@Override
	public Role selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		//打破关系
//		mapper.deleteRelation(record.getId());
		
		int count = mapper.updateByPrimaryKey(record);
		
		
		//重新关联关系
		List<Permission> permissions = record.getPermissions();
		for (Permission permission : permissions) {
			mapper.insertRelation(record.getId(), permission.getId());
		}
		
		return count;
	}
	
	@Override
	public PageResult queryPageResult(QueryObject qo) {
		Long count = mapper.queryPageResultCount(qo);
		//如果有数据就去查询结果集
		if(count>0){
			return new PageResult(count, mapper.queryPageResult(qo));
		}
		
		return new PageResult(count,Collections.EMPTY_LIST);
	}
}
